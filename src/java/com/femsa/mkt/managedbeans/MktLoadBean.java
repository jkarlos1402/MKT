package com.femsa.mkt.managedbeans;

import com.femsa.mkt.analizer.XlsAnalizerDatos;
import com.femsa.mkt.analizer.XlsAnalizerEstadoExcel;
import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.DataBaseException;
import com.femsa.mkt.exception.MKTException;
import com.femsa.mkt.pojos.MktCobhDimAgrupador;
import com.femsa.mkt.pojos.MktCobhDimCategoria;
import com.femsa.mkt.pojos.MktCobhDimEstado;
import com.femsa.mkt.pojos.MktCobhDimGec;
import com.femsa.mkt.pojos.MktCobhDimGrupoRm1;
import com.femsa.mkt.pojos.MktCobhDimMarca;
import com.femsa.mkt.pojos.MktCobhDimRegion;
import com.femsa.mkt.pojos.MktCobhDimRetornable;
import com.femsa.mkt.pojos.MktCobhDimSegcalorico;
import com.femsa.mkt.pojos.MktCobhDimTamanio;
import com.femsa.mkt.pojos.MktCobhDimTerritorio;
import com.femsa.mkt.pojos.MktCobhDimUo;
import com.femsa.mkt.pojos.MktCobhLogDet;
import com.femsa.mkt.pojos.MktCobhLogEnc;
import com.femsa.mkt.pojos.MktCobhLogStatus;
import com.femsa.mkt.pojos.MktProcesoArchivo;
import com.femsa.mkt.util.CarpetaCompartida;
import com.femsa.mkt.util.HiloCargaCobHist;
import com.femsa.mkt.util.MktAdmin_CatalogLoader;
import com.femsa.mkt.util.Record;
import com.femsa.mkt.util.ScriptAnalizer;
import com.femsa.mkt.util.Sonido;
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

@ManagedBean(name = "mktLoadBean")
@ViewScoped
public class MktLoadBean {

    private List<String> omittedSheets;
    private List<String> loadedSheets;
    private List<String> errors;
    private List<Record> cargas;
    private List<String> nombresClave;
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

    public MktLoadBean() {
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        cargas = new ArrayList<Record>();
        nombresClave = new ArrayList<String>();
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
            logCarga.setNombreProceso("CARGA HISTORICA");
            logCarga.setFkProyecto(mainBean.getUsuario().getProyectos().get(0));
            logCarga.setMktCobhLogDetList(new ArrayList<MktCobhLogDet>());
            logsCarga.add(logCarga);
        }
        MktProcesoArchivo procesoArchivo = new MktProcesoArchivo(mainBean.getUsuario());
        XlsAnalizerDatos analizer = new XlsAnalizerDatos();
        try {
            MktAdmin_CatalogLoader.loadCatalogs("MARKETING");
        } catch (MKTException ex) {
            Logger.getLogger(MktLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Agregar aqui los catalogos faltantes
        analizer.analizeXls(event.getFile(),
                (List<MktCobhDimEstado>) context.getAttribute("dimEstado"),
                (List<MktCobhDimRegion>) context.getAttribute("dimRegion"),
                (List<MktCobhDimUo>) context.getAttribute("dimUO"),
                (List<MktCobhDimMarca>) context.getAttribute("dimMarca"),
                (List<MktCobhDimTamanio>) context.getAttribute("dimTamanio"),
                (List<MktCobhDimRetornable>) context.getAttribute("dimRetornable"),
                (List<MktCobhDimCategoria>) context.getAttribute("dimCategoria"),
                (List<MktCobhDimGrupoRm1>) context.getAttribute("dimGrupoRM1"),
                (List<MktCobhDimSegcalorico>) context.getAttribute("dimSegmentoCalorico"),
                (List<MktCobhDimTerritorio>) context.getAttribute("dimTerritorio"),
                (List<MktCobhDimAgrupador>) context.getAttribute("dimAgrupador"),
                (List<MktCobhDimGec>) context.getAttribute("dimGec"));        
        procesoArchivo.setUploadedFile(event.getFile());
//        uploadedFile = event.getFile();
        try {
            procesoArchivo.setStream(procesoArchivo.getUploadedFile().getInputstream());
//            stream = uploadedFile.getInputstream();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        procesoArchivo.setOmittedSheets(analizer.getOmittedSheets());
        for (String omittedSheet : procesoArchivo.getOmittedSheets()) {
            omittedSheets.add(omittedSheet);
        }
        procesoArchivo.setLoadedSheets(analizer.getLoadedSheets());
        for (String loadedSheet : procesoArchivo.getLoadedSheets()) {
            loadedSheets.add(loadedSheet);
        }
        procesoArchivo.setErrors(analizer.getErrors());
        for (String error : procesoArchivo.getErrors()) {
            errors.add(error);
        }
        procesoArchivo.setNombresClave(analizer.getNombresClave());
//        nombresClave = analizer.getNombresClave();
        procesoArchivo.setNumRegistros(analizer.getNumRegistros());
        numRegistros += procesoArchivo.getNumRegistros();
        if (procesoArchivo.getNumRegistros() > 0) {
            procesoArchivo.setNameFile(event.getFile().getFileName());
            logsCarga.get(0).setTotalarchivos(logsCarga.get(0).getTotalarchivos() != null ? logsCarga.get(0).getTotalarchivos() + 1 : 1);
//            nameFile = event.getFile().getFileName();
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
            logsCarga.get(0).setTotalregistros(numRegistros);
        }
        GenericDAO genericDAO = null;
        synchronized (logsCarga) {
            try {
                genericDAO = new GenericDAO();
                genericDAO.saveOrUpdate(logsCarga.get(0));
            } catch (DataBaseException ex) {
                Logger.getLogger(MktLoadBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DAOException ex) {
                Logger.getLogger(MktLoadBean.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (genericDAO != null) {
                    genericDAO.closeDAO();
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void handleFileUploadEstadoExcel(FileUploadEvent event) {
        FacesMessage message = null;
        XlsAnalizerEstadoExcel analizer = new XlsAnalizerEstadoExcel();
        analizer.analizeXls(event.getFile());
        uploadedFile = event.getFile();
        try {
            stream = uploadedFile.getInputstream();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        omittedSheets = analizer.getOmittedSheets();
        loadedSheets = analizer.getLoadedSheets();
        errors = analizer.getErrors();
        numRegistros = analizer.getNumRegistros();
        System.out.println("Registros " + numRegistros);
        if (numRegistros > 0) {
            nameFile = event.getFile().getFileName();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", event.getFile().getFileName() + " Archivo Cargado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", event.getFile().getFileName() + " Error en el Archivo");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void saveInfoDatos() {
        FacesMessage message = null;
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = context.getRealPath("");
        Boolean flagLoadMktDatos = (Boolean) context.getAttribute("BanderaDatos");
        List<MktCobhLogStatus> catStatus = (List<MktCobhLogStatus>) context.getAttribute("catCobhLogStatus");
        Thread[] hilosCarga = new Thread[archivos.size()];
        GenericDAO genericDAO = null;
        if (!flagLoadMktDatos) {
//            System.out.println("paso la bandera de carga");
            flagLoadMktDatos = true;
            context.setAttribute("BanderaDatos", flagLoadMktDatos);
            try {
                genericDAO = new GenericDAO();
                genericDAO.saveOrUpdateAll((List<MktCobhDimEstado>) context.getAttribute("dimEstado"));
                genericDAO.saveOrUpdateAll((List<MktCobhDimRegion>) context.getAttribute("dimRegion"));
                genericDAO.saveOrUpdateAll((List<MktCobhDimUo>) context.getAttribute("dimUO"));
                genericDAO.saveOrUpdateAll((List<MktCobhDimMarca>) context.getAttribute("dimMarca"));
                genericDAO.saveOrUpdateAll((List<MktCobhDimTamanio>) context.getAttribute("dimTamanio"));
                genericDAO.saveOrUpdateAll((List<MktCobhDimRetornable>) context.getAttribute("dimRetornable"));
                genericDAO.saveOrUpdateAll((List<MktCobhDimCategoria>) context.getAttribute("dimCategoria"));
                genericDAO.saveOrUpdateAll((List<MktCobhDimGrupoRm1>) context.getAttribute("dimGrupoRM1"));
                genericDAO.saveOrUpdateAll((List<MktCobhDimSegcalorico>) context.getAttribute("dimSegmentoCalorico"));
                genericDAO.saveOrUpdateAll((List<MktCobhDimTerritorio>) context.getAttribute("dimTerritorio"));
                genericDAO.saveOrUpdateAll((List<MktCobhDimAgrupador>) context.getAttribute("dimAgrupador"));
                genericDAO.saveOrUpdateAll((List<MktCobhDimGec>) context.getAttribute("dimGec"));
                for (int i = 0; i < archivos.size(); i++) {
//                    System.out.println("claves " + archivos.get(i).getNombresClave().size());
                    hilosCarga[i] = new HiloCargaCobHist(archivos.get(i), logsCarga.get(0), genericDAO, catStatus, contextPathResources + File.separator + "WEB-INF");
                    hilosCarga[i].start();
                }
                for (Thread thread : hilosCarga) {
                    thread.join();
                }
                List<String> logScript = new ArrayList<String>();
                if (!ScriptAnalizer.executeScrits(logScript)) {
                    message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al ejecutar el script");
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registros Cargados");
                }
                for (String string : logScript) {
                    System.out.println(string);
                }
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
                    genericDAO.saveOrUpdate(logsCarga.get(0));
                }
            } catch (InterruptedException ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo cargar el Archivo [" + ex.getMessage() + "]");
                Logger.getLogger(MktLoadBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo cargar el Archivo [" + ex.getMessage() + "]");
                Logger.getLogger(MktLoadBean.class.getName()).log(Level.SEVERE, null, ex);
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
            context.setAttribute("BanderaDatos", flagLoadMktDatos);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Disculpe", "Hay otro proceso de Carga Intente más Tarde");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void saveInfoExcel() {
        FacesMessage message = null;
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        Boolean flagLoadMktDatos = (Boolean) context.getAttribute("BanderaDatos");
        if (!flagLoadMktDatos) {
            flagLoadMktDatos = true;
            context.setAttribute("BanderaDatos", flagLoadMktDatos);
            Record record = new Record();
            record.setFecha(new Date());
            record.setNameFile(nameFile);
            record.setProcess("Carga de Archivo de Datos Estados");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerEstadoExcel estadoDatos = new XlsAnalizerEstadoExcel();
            if (estadoDatos.saveSheetInfoDatos(uploadedFile, stream, numRegistros)) {
                record.setNumEntriesSaved(numRegistros);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registros Cargados");
            } else {
                String cadenaError = "";
                for (String error : errors) {
                    cadenaError += error + ", ";
                }
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo cargar el Archivo [" + cadenaError + "]");
            }
            omittedSheets.clear();
            loadedSheets.clear();
            errors.clear();
            numRegistros = 0L;
            record.setDateEndExecution(new Date());
            flagLoadMktDatos = false;
            context.setAttribute("BanderaDatos", flagLoadMktDatos);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Disculpe", "Hay otro proceso de Carga Intente mas Tarde");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void ejecutaScript() {
        FacesMessage message = null;
        try {
            ScriptAnalizer.ejecutaScripts(mainBean.getPageVentana(),"llenaUniverso");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Script(s) ejecutado(s)");
        } catch (MKTException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", ex.getMessage());
//            Logger.getLogger(MktLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Disculpe", ex.getMessage());
//            Logger.getLogger(MktLoadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
}
