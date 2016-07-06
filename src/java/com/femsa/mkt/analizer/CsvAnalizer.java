package com.femsa.mkt.analizer;

import com.csvreader.CsvReader;
import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.DataBaseException;
import com.femsa.mkt.exception.MKTException;
import com.femsa.mkt.pojos.MktAdmin_Usuario;
import com.femsa.mkt.pojos.MktCobhRelarchivotabla;
import com.femsa.mkt.pojos.MktCobhStHisDatos;
import com.femsa.mkt.util.Encabezado;
import com.femsa.mkt.util.EncabezadoIndex;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.servlet.ServletContext;
import org.hibernate.Session;
import org.primefaces.model.UploadedFile;

public class CsvAnalizer {

    private List<String> errorsScript = new ArrayList<String>();
    private List<String> omittedSheets;
    private List<String> loadedSheets;
    private List<String> errors;
    private List<MktCobhStHisDatos> cargasInfoDatos = new ArrayList<MktCobhStHisDatos>();
    private long numRegistros = 0L;  
    private List<String> nombresClave;
    private List<EncabezadoIndex> encabezadosCsv;
    private List<MktCobhRelarchivotabla> encabezadosTodos;

    public CsvAnalizer() {
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        nombresClave = new ArrayList<String>();
    }

    public List<MktCobhRelarchivotabla> getEncabezadosTodos() {
        return encabezadosTodos;
    }

    public void setEncabezadosTodos(List<MktCobhRelarchivotabla> encabezadosTodos) {
        this.encabezadosTodos = encabezadosTodos;
    }

    public List<EncabezadoIndex> getEncabezadosCsv() {
        return encabezadosCsv;
    }

    public void setEncabezadosCsv(List<EncabezadoIndex> encabezadosCsv) {
        this.encabezadosCsv = encabezadosCsv;
    }

    public List<String> getNombresClave() {
        return nombresClave;
    }

    public void setNombresClave(List<String> nombresClave) {
        this.nombresClave = nombresClave;
    }

    public List<String> getErrorsScript() {
        return errorsScript;
    }

    public void setErrorsScript(List<String> errorsScript) {
        this.errorsScript = errorsScript;
    }

    public long getNumRegistros() {
        return numRegistros;
    }

    public void setNumRegistros(long numRegistros) {
        this.numRegistros = numRegistros;
    }

    public List<String> getOmittedSheets() {
        return omittedSheets;
    }

    public void setOmittedSheets(List<String> omittedSheets) {
        this.omittedSheets = omittedSheets;
    }

    public List<String> getLoadedSheets() {
        return loadedSheets;
    }

    public void setLoadedSheets(List<String> loadedSheets) {
        this.loadedSheets = loadedSheets;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<MktCobhStHisDatos> getCargasInfoDatos() {
        return cargasInfoDatos;
    }

    public void setCargasInfoDatos(List<MktCobhStHisDatos> cargasInfoDatos) {
        this.cargasInfoDatos = cargasInfoDatos;
    }

    public void analizeCsv(UploadedFile file, List<MktCobhRelarchivotabla> camposCsvBase, ServletContext context) throws MKTException {
        CsvReader csvReader = null;
        InputStream inputStream = null;
        String[] encabezadosCsvArray = null;
        List<Encabezado> listaEncabezadosCsv = new ArrayList<Encabezado>();
        List<EncabezadoIndex> listaEncabezadosIndexCsv = new ArrayList<EncabezadoIndex>();
        int numRegistrosTmp = 0;
        try {
            inputStream = file.getInputstream();
            csvReader = new CsvReader(inputStream, Charset.forName("UTF-8"));
            csvReader.readHeaders();
            encabezadosCsvArray = csvReader.getHeaders();
            int i = 0;
            for (String encabezado : encabezadosCsvArray) {                
                listaEncabezadosCsv.add(new Encabezado(encabezado, i));
                i++;
            }
            for (MktCobhRelarchivotabla campoCsvBase : camposCsvBase) {
                if (campoCsvBase.getRequerido() && !listaEncabezadosCsv.contains(new Encabezado(campoCsvBase.getCampoArchivo(), 0))) {
                    throw new MKTException("Columna [ " + campoCsvBase.getCampoArchivo() + " ] requerida [ " + file.getFileName() + " ]");
                } else {
                    listaEncabezadosIndexCsv.add(new EncabezadoIndex(null, listaEncabezadosCsv.get(listaEncabezadosCsv.indexOf(new Encabezado(campoCsvBase.getCampoArchivo(), 0))).getIndex(), null, null, campoCsvBase));
                }
            }
            for (Encabezado encabezado : listaEncabezadosCsv) {
                if (!"".equals(encabezado.getNombreEncabezado().trim()) && !camposCsvBase.contains(new MktCobhRelarchivotabla(encabezado.getNombreEncabezado()))) {
                    throw new MKTException("Columna " + encabezado.getNombreEncabezado() + " inválida [ " + file.getFileName() + " ]");
                }
            }
            while (csvReader.readRecord()) {
                if (csvReader.get(0) == null || "".equals(csvReader.get(0).trim())) {
                    continue;
                }
                numRegistrosTmp++;

            }
            numRegistros = numRegistrosTmp;
            this.encabezadosCsv = listaEncabezadosIndexCsv;
        } catch (IOException ex) {
            throw new MKTException(ex.getMessage());
        } finally {
            try {
                if (csvReader != null) {
                    csvReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                throw new MKTException(ex.getMessage());
            }
        }
    }

    public void saveCsv(UploadedFile file, InputStream stream, MktAdmin_Usuario usuario,Class clase) throws MKTException {       
        CsvReader csvReader = null;
        InputStream inputStream = null;
        Object registroTmp;
        Field[] camposLzObjetivo = clase.getDeclaredFields();
        GenericDAO genericDAO = null;
        long cont = 0L;
        Session session;       
        try {
            genericDAO = new GenericDAO();
            session = genericDAO.getSession();
            inputStream = file.getInputstream();
            csvReader = new CsvReader(inputStream, Charset.forName("UTF-8"));
            csvReader.readHeaders();
            String[] data;
            session.beginTransaction();
            while (csvReader.readRecord()) {
                data = csvReader.getValues();               
                if (csvReader.get(0) == null || "".equals(csvReader.get(0).trim())) {
                    continue;
                }
                registroTmp = clase.newInstance();
                for (EncabezadoIndex encabezado : encabezadosCsv) {
                    for (Field field : camposLzObjetivo) {
                        if (field.isAnnotationPresent(Column.class) && field.getAnnotation(Column.class).name().equals(encabezado.getCampoTabla().getCampoTabla())) {
                            field.setAccessible(true);
                            if (field.getType().getName().equals(Double.class.getName())) {
                                field.set(registroTmp, data[encabezado.getIndex()] != null && !"".equals(data[encabezado.getIndex()].trim()) ? Double.parseDouble(data[encabezado.getIndex()]) : null);
                            } else if (field.getType().getName().equals(Integer.class.getName())) {
                                field.set(registroTmp, data[encabezado.getIndex()] != null && !"".equals(data[encabezado.getIndex()].trim()) ? Integer.parseInt(data[encabezado.getIndex()]) : null);
                            } else if (field.getType().getName().equals(Long.class.getName())) {
                                field.set(registroTmp, data[encabezado.getIndex()] != null && !"".equals(data[encabezado.getIndex()].trim()) ? Long.parseLong(data[encabezado.getIndex()]) : null);
                            }  else if (field.getType().getName().equals(Float.class.getName())) {
                                field.set(registroTmp, data[encabezado.getIndex()] != null && !"".equals(data[encabezado.getIndex()].trim()) ? Float.parseFloat(data[encabezado.getIndex()]) : null);
                            } else{
                                field.set(registroTmp, data[encabezado.getIndex()] != null ? data[encabezado.getIndex()].toUpperCase() : "");
                            }
                        }
                    }
                }                
                genericDAO.saveOrUpdate((Serializable)registroTmp, false);
                if (cont % 100 == 0) {
                    session.flush();
                    session.clear();
                    cont = 0L;
                }
                cont++;
            }
            session.getTransaction().commit();
        } catch (IllegalArgumentException ex) {
//            ex.printStackTrace();
            throw new MKTException(ex.getMessage());
        } catch (IllegalAccessException ex) {
//            ex.printStackTrace();
            throw new MKTException(ex.getMessage());
        } catch (DataBaseException ex) {
//            ex.printStackTrace();
            throw new MKTException(ex.getMessage());
        } catch (DAOException ex) {
//            ex.printStackTrace();
            throw new MKTException(ex.getMessage());
        } catch (IOException ex) {
//            ex.printStackTrace();
            throw new MKTException(ex.getMessage());
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new MKTException(ex.getMessage());
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
            try {
                if (csvReader != null) {
                    csvReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex1) {
                ex1.printStackTrace();
                throw new MKTException(ex1.getMessage());
            }
        }
    }    
}
