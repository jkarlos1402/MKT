package com.femsa.mkt.managedbeans.lanzamientos;

import com.femsa.mkt.analizer.CsvAnalizer;
import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.DataBaseException;
import com.femsa.mkt.exception.MKTException;
import com.femsa.mkt.managedbeans.MktAdmin_MainBean;
import com.femsa.mkt.pojos.MktCobhLogDet;
import com.femsa.mkt.pojos.MktCobhLogEnc;
import com.femsa.mkt.pojos.MktCobhLogStatus;
import com.femsa.mkt.pojos.MktCobhRelarchivotabla;
import com.femsa.mkt.pojos.MktProcesoArchivo;
import com.femsa.mkt.pojos.MktTmpLzFiltroIdCliente;
import com.femsa.mkt.pojos.MktTmpLzObjetivo;
import com.femsa.mkt.util.DetalleCarga;
import com.femsa.mkt.util.HiloCargaCsv;
import com.femsa.mkt.util.Record;
import com.femsa.mkt.util.ScriptAnalizer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "mktLanzamientosLoadBean")
@ViewScoped
public class MktLanzamientosLoadBean {

    private List<String> omittedSheets;
    private List<String> loadedSheets;
    private List<String> errors;
    private List<Record> cargas;
    private String nameFile;
    private long numRegistros;
    private Long numProcesados = 0L;
    private Long numFaltantes = 0L;

    private UploadedFile uploadedFile;
    private InputStream stream;

    private SimpleDateFormat formatDay = new SimpleDateFormat("dd/MM/yy");
    private SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");

    @ManagedProperty(value = "#{mainBean}")
    private MktAdmin_MainBean mainBean;

    private List<MktProcesoArchivo> archivos;

    private List<MktCobhLogEnc> logsCarga;

    private boolean bndMuestraChoose = true;

    private List<DetalleCarga> detalleCarga;

    private Object[] detalleUltimaEjecucion;

    public MktLanzamientosLoadBean() {
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        cargas = new ArrayList<Record>();
    }

    public Object[] getDetalleUltimaEjecucion() {
        return detalleUltimaEjecucion;
    }

    public void setDetalleUltimaEjecucion(Object[] detalleUltimaEjecucion) {
        this.detalleUltimaEjecucion = detalleUltimaEjecucion;
    }

    public List<DetalleCarga> getDetalleCarga() {
        return detalleCarga;
    }

    public void setDetalleCarga(List<DetalleCarga> detalleCarga) {
        this.detalleCarga = detalleCarga;
    }

    public boolean isBndMuestraChoose() {
        return bndMuestraChoose;
    }

    public void setBndMuestraChoose(boolean bndMuestraChoose) {
        this.bndMuestraChoose = bndMuestraChoose;
    }

    public SimpleDateFormat getFormatFecha() {
        return formatFecha;
    }

    public void setFormatFecha(SimpleDateFormat formatFecha) {
        this.formatFecha = formatFecha;
    }

    public SimpleDateFormat getFormatHora() {
        return formatHora;
    }

    public void setFormatHora(SimpleDateFormat formatHora) {
        this.formatHora = formatHora;
    }

    public List<MktCobhLogEnc> getLogsCarga() {
        return logsCarga;
    }

    public void setLogsCarga(List<MktCobhLogEnc> logsCarga) {
        this.logsCarga = logsCarga;
    }

    public List<MktProcesoArchivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<MktProcesoArchivo> archivos) {
        this.archivos = archivos;
    }

    public MktAdmin_MainBean getMainBean() {
        return mainBean;
    }

    public void setMainBean(MktAdmin_MainBean mainBean) {
        this.mainBean = mainBean;
    }

    public long getNumProcesados() {
        return numProcesados;
    }

    public void setNumProcesados(long numProcesados) {
        this.numProcesados = numProcesados;
    }

    public long getNumFaltantes() {
        return numFaltantes;
    }

    public void setNumFaltantes(long numFaltantes) {
        this.numFaltantes = numFaltantes;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public long getNumRegistros() {
        return numRegistros;
    }

    public void setNumRegistros(long numRegistros) {
        this.numRegistros = numRegistros;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public SimpleDateFormat getFormatDay() {
        return formatDay;
    }

    public void setFormatDay(SimpleDateFormat formatDay) {
        this.formatDay = formatDay;
    }

    public List<Record> getCargas() {
        return cargas;
    }

    public void setCargas(List<Record> cargas) {
        this.cargas = cargas;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getLoadedSheets() {
        return loadedSheets;
    }

    public void setLoadedSheets(List<String> loadedSheets) {
        this.loadedSheets = loadedSheets;
    }

    public List<String> getOmittedSheets() {
        return omittedSheets;
    }

    public void setOmittedSheets(List<String> omittedSheets) {
        this.omittedSheets = omittedSheets;
    }

    public void handleFileUploadMktDatos(FileUploadEvent event) {
        FacesMessage message = null;
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        List<MktCobhLogStatus> catStatus = (List<MktCobhLogStatus>) context.getAttribute("catCobhLogStatus");
        if (logsCarga == null) {
            MktCobhLogEnc logCarga = new MktCobhLogEnc();
            logsCarga = new ArrayList<MktCobhLogEnc>();
            logCarga.setInicio(new Date());
            logCarga.setFkIdstatus(catStatus.get(catStatus.indexOf(new MktCobhLogStatus(0))));
            logCarga.setFkUsuario(mainBean.getUsuario());
            logCarga.setNombreProceso("CARGA OBJETIVOS");
            logCarga.setFkProyecto(mainBean.getUsuario().getProyectos().get(0));
            logCarga.setMktCobhLogDetList(new ArrayList<MktCobhLogDet>());
            logsCarga.add(logCarga);
        }
        MktProcesoArchivo procesoArchivo = new MktProcesoArchivo(mainBean.getUsuario());
        CsvAnalizer analizer = new CsvAnalizer();
//        GenericDAO genericDAO = null;
        try {
            analizer.analizeCsv(event.getFile(), (List<MktCobhRelarchivotabla>) context.getAttribute("camposLanzObjetivos"), context);
            procesoArchivo.setEncabezadosTodos((List<MktCobhRelarchivotabla>) context.getAttribute("camposLanzObjetivos"));
            procesoArchivo.setEncabezadosCsv(analizer.getEncabezadosCsv());
            procesoArchivo.setUploadedFile(event.getFile());
            procesoArchivo.setStream(procesoArchivo.getUploadedFile().getInputstream());
            procesoArchivo.setErrors(analizer.getErrors());
            for (String error : procesoArchivo.getErrors()) {
                errors.add(error);
            }
            procesoArchivo.setNumRegistros(analizer.getNumRegistros());
            numRegistros += procesoArchivo.getNumRegistros();
            if (procesoArchivo.getNumRegistros() > 0) {
                procesoArchivo.setNameFile(event.getFile().getFileName());
                logsCarga.get(0).setTotalarchivos(logsCarga.get(0).getTotalarchivos() != null ? logsCarga.get(0).getTotalarchivos() + 1 : 1);
                nameFile = event.getFile().getFileName();
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", event.getFile().getFileName() + " Archivo Cargado");
            } else {
                //logsCarga.get(0).setTotalarchivos(logsCarga.get(0).getTotalarchivos() != null ? logsCarga.get(0).getTotalarchivos() - 1 : 0);
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", event.getFile().getFileName() + " Error en el Archivo");
            }
            if (archivos == null) {
                archivos = new ArrayList<MktProcesoArchivo>();
            }
            if (procesoArchivo.getNumRegistros() > 0) {
                archivos.add(procesoArchivo);
            }
            synchronized (logsCarga) {
//                genericDAO = new GenericDAO();
                logsCarga.get(0).setTotalregistros(numRegistros);
//                genericDAO.saveOrUpdate(logsCarga.get(0));
//
            }
        } catch (MKTException ex) {
            errors.add(ex.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
        } catch (IOException ex) {
            errors.add(ex.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
        } // catch (DataBaseException ex) {
//            errors.add(ex.getMessage());
//            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
//        } catch (DAOException ex) {
//            errors.add(ex.getMessage());
//            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
//        } finally {
//            if (genericDAO != null) {
//                genericDAO.closeDAO();
//            }
//        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void handleFileUploadMktDatosCliente(FileUploadEvent event) {
        FacesMessage message = null;
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        List<MktCobhLogStatus> catStatus = (List<MktCobhLogStatus>) context.getAttribute("catCobhLogStatus");
        if (logsCarga == null) {
            MktCobhLogEnc logCarga = new MktCobhLogEnc();
            logsCarga = new ArrayList<MktCobhLogEnc>();
            logCarga.setInicio(new Date());
            logCarga.setFkIdstatus(catStatus.get(catStatus.indexOf(new MktCobhLogStatus(0))));
            logCarga.setFkUsuario(mainBean.getUsuario());
            logCarga.setNombreProceso("CARGA ID CLIENTE");
            logCarga.setFkProyecto(mainBean.getUsuario().getProyectos().get(0));
            logCarga.setMktCobhLogDetList(new ArrayList<MktCobhLogDet>());
            logsCarga.add(logCarga);
        }
        MktProcesoArchivo procesoArchivo = new MktProcesoArchivo(mainBean.getUsuario());
        CsvAnalizer analizer = new CsvAnalizer();
//        GenericDAO genericDAO = null;
        try {
            analizer.analizeCsv(event.getFile(), (List<MktCobhRelarchivotabla>) context.getAttribute("camposLanzIdCliente"), context);
            procesoArchivo.setEncabezadosTodos((List<MktCobhRelarchivotabla>) context.getAttribute("camposLanzIdCliente"));
            procesoArchivo.setEncabezadosCsv(analizer.getEncabezadosCsv());
            procesoArchivo.setUploadedFile(event.getFile());
            procesoArchivo.setStream(procesoArchivo.getUploadedFile().getInputstream());
            procesoArchivo.setErrors(analizer.getErrors());
            for (String error : procesoArchivo.getErrors()) {
                errors.add(error);
            }
            procesoArchivo.setNumRegistros(analizer.getNumRegistros());
            numRegistros += procesoArchivo.getNumRegistros();
            if (procesoArchivo.getNumRegistros() > 0) {
                procesoArchivo.setNameFile(event.getFile().getFileName());
                logsCarga.get(0).setTotalarchivos(logsCarga.get(0).getTotalarchivos() != null ? logsCarga.get(0).getTotalarchivos() + 1 : 1);
                nameFile = event.getFile().getFileName();
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", event.getFile().getFileName() + " Archivo Cargado");
            } else {
                //logsCarga.get(0).setTotalarchivos(logsCarga.get(0).getTotalarchivos() != null ? logsCarga.get(0).getTotalarchivos() - 1 : 0);
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", event.getFile().getFileName() + " Error en el Archivo");
            }
            if (archivos == null) {
                archivos = new ArrayList<MktProcesoArchivo>();
            }
            if (procesoArchivo.getNumRegistros() > 0) {
                archivos.add(procesoArchivo);
            }
            synchronized (logsCarga) {
//                genericDAO = new GenericDAO();
                logsCarga.get(0).setTotalregistros(numRegistros);
//                genericDAO.saveOrUpdate(logsCarga.get(0));
//
            }
        } catch (MKTException ex) {
            errors.add(ex.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
        } catch (IOException ex) {
            errors.add(ex.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
        } // catch (DataBaseException ex) {
//            errors.add(ex.getMessage());
//            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
//        } catch (DAOException ex) {
//            errors.add(ex.getMessage());
//            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
//        } finally {
//            if (genericDAO != null) {
//                genericDAO.closeDAO();
//            }
//        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void saveInfoDatos() {
        FacesMessage message = null;
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = context.getRealPath("");
        Boolean flagLoadMktDatos = (Boolean) context.getAttribute("BanderaDatosLanzamientosObjetivo");
        List<MktCobhLogStatus> catStatus = (List<MktCobhLogStatus>) context.getAttribute("catCobhLogStatus");
        Thread[] hilosCarga = new Thread[archivos.size()];
        GenericDAO genericDAO = null;
        if (!flagLoadMktDatos) {
//            System.out.println("paso la bandera de carga");
            flagLoadMktDatos = true;
            context.setAttribute("BanderaDatosLanzamientosObjetivo", flagLoadMktDatos);
            try {
                genericDAO = new GenericDAO();
//                MktAdmin_CatalogLoader.saveCatalogosDinamicos();
//                MktAdmin_CatalogLoader.loadCatalogsDinamicos("com.femsa.mkt.pojos", "MARKETING");
                ejecutaScript(1);
                for (int i = 0; i < archivos.size(); i++) {
//                    System.out.println("claves " + archivos.get(i).getNombresClave().size());
                    hilosCarga[i] = new HiloCargaCsv(archivos.get(i), logsCarga.get(0), genericDAO, catStatus, contextPathResources + File.separator + "WEB-INF",MktTmpLzObjetivo.class);
                    hilosCarga[i].start();
                }
                for (Thread thread : hilosCarga) {
                    thread.join();
                }
//                List<String> logScript = new ArrayList<String>();
////                if (!ScriptAnalizer.executeScrits(logScript)) {
////                    message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al ejecutar el script");
////                } else {                    
////                }
//                for (String string : logScript) {
//                    System.out.println(string);
//                }
                ejecutaScript(2);
                synchronized (logsCarga) {
                    logsCarga.get(0).setFinal1(new Date());
                    int tiempo = ((int) ((new Date()).getTime() - logsCarga.get(0).getInicio().getTime())) / 1000;
                    logsCarga.get(0).setTiempo(tiempo);
                    logsCarga.get(0).setFkIdstatus(catStatus.get(catStatus.indexOf(new MktCobhLogStatus(1))));
                    logsCarga.get(0).setTotalagregados(logsCarga.get(0).getTotalregistros());
                    logsCarga.get(0).setTotalerror(0);
                    logsCarga.get(0).setTotalprocesados(logsCarga.get(0).getTotalregistros());
                    logsCarga.get(0).setTotaleliminados(0);
                    logsCarga.get(0).setTotalactualizados(0);
//                    genericDAO.saveOrUpdate(logsCarga.get(0));
                }
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registros Cargados");
            } catch (InterruptedException ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo cargar el Archivo [" + ex.getMessage() + "]");
            } catch (Exception ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo cargar el Archivo [" + ex.getMessage() + "]");
            } finally {
                if (genericDAO != null) {
                    genericDAO.closeDAO();
                }
            }
            archivos.clear();
            omittedSheets.clear();
            loadedSheets.clear();
            errors.clear();
            numRegistros = 0L;
            bndMuestraChoose = false;
            //mainBean.setPage(null);
//            Sonido.ejecuta();            
            flagLoadMktDatos = false;
            context.setAttribute("BanderaDatosLanzamientosObjetivo", flagLoadMktDatos);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Disculpe", "Hay otro proceso de Carga Intente más Tarde");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void saveInfoDatosCliente() {
        FacesMessage message = null;
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = context.getRealPath("");
        Boolean flagLoadMktDatos = (Boolean) context.getAttribute("BanderaDatosLanzamientosIdCliente");
        List<MktCobhLogStatus> catStatus = (List<MktCobhLogStatus>) context.getAttribute("catCobhLogStatus");
        Thread[] hilosCarga = new Thread[archivos.size()];
        GenericDAO genericDAO = null;
        if (!flagLoadMktDatos) {
//            System.out.println("paso la bandera de carga");
            flagLoadMktDatos = true;
            context.setAttribute("BanderaDatosLanzamientosIdCliente", flagLoadMktDatos);
            try {
                genericDAO = new GenericDAO();
//                MktAdmin_CatalogLoader.saveCatalogosDinamicos();
//                MktAdmin_CatalogLoader.loadCatalogsDinamicos("com.femsa.mkt.pojos", "MARKETING");
                ejecutaScript(3);
                for (int i = 0; i < archivos.size(); i++) {
//                    System.out.println("claves " + archivos.get(i).getNombresClave().size());
                    hilosCarga[i] = new HiloCargaCsv(archivos.get(i), logsCarga.get(0), genericDAO, catStatus, contextPathResources + File.separator + "WEB-INF",MktTmpLzFiltroIdCliente.class);
                    hilosCarga[i].start();
                }
                for (Thread thread : hilosCarga) {
                    thread.join();
                }
//                List<String> logScript = new ArrayList<String>();
////                if (!ScriptAnalizer.executeScrits(logScript)) {
////                    message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al ejecutar el script");
////                } else {                    
////                }
//                for (String string : logScript) {
//                    System.out.println(string);
//                }
                ejecutaScript(4);
                synchronized (logsCarga) {
                    logsCarga.get(0).setFinal1(new Date());
                    int tiempo = ((int) ((new Date()).getTime() - logsCarga.get(0).getInicio().getTime())) / 1000;
                    logsCarga.get(0).setTiempo(tiempo);
                    logsCarga.get(0).setFkIdstatus(catStatus.get(catStatus.indexOf(new MktCobhLogStatus(1))));
                    logsCarga.get(0).setTotalagregados(logsCarga.get(0).getTotalregistros());
                    logsCarga.get(0).setTotalerror(0);
                    logsCarga.get(0).setTotalprocesados(logsCarga.get(0).getTotalregistros());
                    logsCarga.get(0).setTotaleliminados(0);
                    logsCarga.get(0).setTotalactualizados(0);
//                    genericDAO.saveOrUpdate(logsCarga.get(0));
                }
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registros Cargados");
            } catch (InterruptedException ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo cargar el Archivo [" + ex.getMessage() + "]");
            } catch (Exception ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo cargar el Archivo [" + ex.getMessage() + "]");
            } finally {
                if (genericDAO != null) {
                    genericDAO.closeDAO();
                }
            }
            archivos.clear();
            omittedSheets.clear();
            loadedSheets.clear();
            errors.clear();
            numRegistros = 0L;
            bndMuestraChoose = false;
            //mainBean.setPage(null);
//            Sonido.ejecuta();            
            flagLoadMktDatos = false;
            context.setAttribute("BanderaDatosLanzamientosIdCliente", flagLoadMktDatos);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Disculpe", "Hay otro proceso de Carga Intente más Tarde");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void ejecutaScript(int tipo) {
        FacesMessage message = null;
        boolean bndCorrecto = true;
        try {
            switch (tipo) {
                case 1:
                    bndCorrecto = ScriptAnalizer.ejecutaScripts("\\WEB-INF\\pages\\lanz\\", "objetivosETL\\pre");
                    break;
                case 2:
                    bndCorrecto = ScriptAnalizer.ejecutaScripts("\\WEB-INF\\pages\\lanz\\", "objetivosETL\\post");
                    break;
                case 3:
                    bndCorrecto = ScriptAnalizer.ejecutaScripts("\\WEB-INF\\pages\\lanz\\", "idClienteETL\\pre");
                    break;
                case 4:
                    bndCorrecto = ScriptAnalizer.ejecutaScripts("\\WEB-INF\\pages\\lanz\\", "idClienteETL\\post");
                    break;
                case 5:
                    bndCorrecto = ScriptAnalizer.ejecutaScripts("\\WEB-INF\\pages\\lanz\\", "aut");
                    break;
            }
            if (bndCorrecto) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Script(s) ejecutado(s)");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Script(s) ejecutado(s) con error");
            }
        } catch (MKTException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", ex.getMessage());
//            Logger.getLogger(MktLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Disculpe", ex.getMessage());
//            Logger.getLogger(MktLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void getDetalleUltimaCarga() {
        GenericDAO genericDAO = null;
        List<Object> resSql;       
        try {
            genericDAO = new GenericDAO();
            resSql = genericDAO.excecuteNativeSQL("SELECT * FROM (SELECT A.ID_REGISTRO, CAST(B.DESC_SCRIPT AS VARCHAR(100)) , CAST(C.DESC_PROYECTO AS VARCHAR(100)), CAST(DESC_ESTATUS AS VARCHAR(100)), CAST(G.DESC_TIPO_SCRIPT AS VARCHAR(100)), TO_CHAR(A.FEC_INICIO,'DD/MM/YYYY'), TO_CHAR(A.FEC_INICIO,'hh24:mi:ss'),NVL(TO_CHAR(A.FEC_FIN,'hh24:mi:ss'),'-'), NVL(A.CANTIDAD_REG,0), TRUNC((NVL(A.FEC_FIN,SYSDATE) - A.FEC_INICIO) * (60 * 24)),A.AVANCE,NVL(A.CANTIDAD_BAD,0),NVL(A.CANTIDAD_TOT,0),NVL(A.CANTIDAD_ST,0) FROM TBLLOGREGIST A LEFT OUTER JOIN TBLLOGSCRIPT B ON A.ID_SCRIPT=B.ID_SCRIPT LEFT OUTER JOIN TBLLOGPROYECT C ON B.ID_PROYECTO=C.ID_PROYECTO LEFT OUTER JOIN TBLLOGREGEST D ON A.ID_REGISTRO=D.ID_REGISTRO LEFT OUTER JOIN CATLOGESTATU E ON D.ID_ESTATUS=E.ID_ESTATUS LEFT OUTER JOIN TBLLOGTIPOSC F ON B.ID_SCRIPT=F.ID_SCRIPT LEFT OUTER JOIN CATLOGTIPOSC G ON F.ID_TIPO_SCRIPT=G.ID_TIPO_SCRIPT WHERE C.DESC_PROYECTO='MARKETING' AND B.DESC_SCRIPT LIKE 'LANZAMIENTO%' ORDER BY A.ID_REGISTRO DESC)A WHERE ROWNUM=1");            
            detalleUltimaEjecucion = (Object[]) resSql.get(0);            
        } catch (DataBaseException ex) {
            Logger.getLogger(MktLanzamientosLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            ex.printStackTrace();
            Logger.getLogger(MktLanzamientosLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
//        detalleCarga = valoresResultado;
    }
}
