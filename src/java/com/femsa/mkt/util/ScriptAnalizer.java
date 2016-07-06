package com.femsa.mkt.util;

import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.DataBaseException;
import com.femsa.mkt.exception.MKTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author TMXIDSJPINAM
 */
public class ScriptAnalizer {

    private static final String MSG_ERROR_TITULO = "Mensaje de error...";

    /**
     *
     */
    public ScriptAnalizer() {
    }

    /**
     *
     * @param errors
     * @return
     */
    public static boolean executeScrits(List<String> errors) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = sc.getRealPath("");
        File directorioBase = new File(contextPathResources + File.separator + "WEB-INF" + File.separator + "script" + File.separator + "cobh" + File.separator);
        File[] ficheros = directorioBase.listFiles();
        List<String> instructions = null;
        session.beginTransaction();
        Query queryNativo = null;
        boolean flagOk = true;
        try {
            for (int i = 0; i < ficheros.length; i++) {
                File fichero = ficheros[i];
                instructions = getInstructionsSQL(fichero, errors);
                if (instructions != null) {
                    for (String instruction : instructions) {
                        System.out.println("instruccion: " + instruction);
                        queryNativo = session.createSQLQuery(instruction);
                        queryNativo.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ScriptAnalizer.class.getName()).log(Level.SEVERE, MSG_ERROR_TITULO, e);
            errors.add("Error running script: " + e.getMessage());
            flagOk = false;
        } finally {
            session.flush();
            session.clear();
            session.close();
            hibernateUtil.closeSessionFactory();
        }
        return flagOk;
    }

    public static boolean executeScrits(List<String> errors, String nombreArchivo) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = sc.getRealPath("");

        String directorioBase = contextPathResources + File.separator + "WEB-INF" + File.separator + "script" + File.separator;

        File fichero = new File(directorioBase + nombreArchivo);
        System.out.println(fichero);
        List<String> instructions = null;
        session.beginTransaction();
        Query queryNativo = null;
        boolean flagOk = true;
        try {
            instructions = getInstructionsSQL(fichero, errors);
            if (instructions != null) {
                for (String instruction : instructions) {
                    System.out.println("instruccion: " + instruction);
                    queryNativo = session.createSQLQuery(instruction);
                    queryNativo.executeUpdate();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ScriptAnalizer.class.getName()).log(Level.SEVERE, MSG_ERROR_TITULO, e);
            errors.add("Error running script: " + e.getMessage());
            flagOk = false;
        } finally {
            session.flush();
            session.clear();
            session.close();
            hibernateUtil.closeSessionFactory();
        }
        return flagOk;
    }

    public static boolean executeScritp(String ruta, String nombre, List<String> logs) throws IOException, MKTException {
        File fichero = new File(ruta + File.separator + nombre);
        System.out.println(fichero);
        List<String> instructions = null;
        GenericDAO genericDAO = null;
        boolean correcto = true;
        try {
            genericDAO = new GenericDAO();
            instructions = getInstructionsSQL(fichero, null);
            if (instructions != null) {
                for (String instruction : instructions) {
                    System.out.println("instruccion: " + instruction);
                    try {
                        genericDAO.excecuteNativeDDLSQL(instruction);
                        logs.add(instruction + ": ejectutada correctamente");
                    } catch (DAOException ex) {
                        if (logs != null) {
                            logs.add(instruction + ": " + ex.getMessage());
                        }
                        correcto = false;
                    }
                }
            }
        } catch (DataBaseException ex) {
            Logger.getLogger(ScriptAnalizer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
        return correcto;
    }

    public static boolean ejecutaScripts(String rutaPaginaActual, String carperta) throws MKTException, IOException {
        String[] rutas = rutaPaginaActual.split("/");
        GenericDAO genericDAO = null;
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = sc.getRealPath("");
        File directorioBase = new File(contextPathResources + rutaPaginaActual + File.separator + carperta);
        if (!directorioBase.isDirectory()) {
            directorioBase.mkdirs();

        }
        System.out.println("DIR: " + directorioBase);
        File[] ficheros = directorioBase.listFiles();
        List<String> instructions = null;
        boolean bndCorrecto = true;
        try {
            genericDAO = new GenericDAO();
            for (int i = 0; i < ficheros.length; i++) {
                File fichero = ficheros[i];
                instructions = getInstructionsSQL(fichero, null);
                if (instructions != null) {
                    for (String instruction : instructions) {
                        try {
                            System.out.println("instruccion: " + instruction);
                            genericDAO.excecuteNativeDDLSQL(instruction);
                        } catch (DAOException ex) {
                            bndCorrecto = false;
//                            ex.printStackTrace();
//                            Logger.getLogger(ScriptAnalizer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (DataBaseException e) {
            Logger.getLogger(ScriptAnalizer.class.getName()).log(Level.SEVERE, MSG_ERROR_TITULO, e);

        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
        return bndCorrecto;
    }

    private static List<String> getInstructionsSQL(File scritpSQL, List<String> errors) throws MKTException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        FileReader f = null;
        BufferedReader b = null;
        String cadena = "";
        String nombre = scritpSQL.getAbsoluteFile().getName().trim();
        int indexComentario = 0;
        List<String> statements = null;
        if (getExtension(nombre).equalsIgnoreCase("sql")) {
            try {
                f = new FileReader(scritpSQL);
                b = new BufferedReader(f);
                while ((cadena = b.readLine()) != null) {
                    cadena = cadena.trim();
                    if (!cadena.startsWith("/*", 0) && !cadena.startsWith("*", 0) && !cadena.startsWith("--", 0) && !cadena.equals("") && !cadena.equalsIgnoreCase("exit;")) {
                        if (cadena.contains("/*")) {
                            indexComentario = cadena.indexOf("/*");
                            cadena = cadena.substring(0, indexComentario);
                        }
                        stringBuilder.append(cadena);
                        stringBuilder.append(" ");
                        if (cadena.endsWith(";")) {
                            if (statements == null) {
                                statements = new ArrayList<String>();
                            }
                            statements.add(stringBuilder.toString().replaceAll(";", "").trim());
                            stringBuilder = new StringBuilder();
                        }
                    }
                }

            } catch (IOException ex) {
                throw new MKTException(ex.getMessage());
            } finally {
                if (b != null) {
                    b.close();
                }
                if (f != null) {
                    f.close();
                }

            }

        }
        return statements;
    }

    private static String getExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }
}
