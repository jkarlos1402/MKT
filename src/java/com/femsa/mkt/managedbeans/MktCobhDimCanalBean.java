package com.femsa.mkt.managedbeans;

import com.femsa.mkt.analizer.XlsAnalizerCanalExcel;
import com.femsa.mkt.dao.MktCobhDimCanalDao;
import com.femsa.mkt.pojos.MktCobhDimCanal;
import com.femsa.mkt.util.Record;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "mktCobhDimCanalBean")
@SessionScoped
public class MktCobhDimCanalBean {
    private MktCobhDimCanal canalNuevo;
    private MktCobhDimCanal canalSeleccionado;
    private List<MktCobhDimCanal> canales;
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

    public MktCobhDimCanalBean() {
        MktCobhDimCanalDao canalDao = new MktCobhDimCanalDao();
        canales = canalDao.getCanalesAll();
        canalNuevo = new MktCobhDimCanal();
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        cargas = new ArrayList<Record>();
    }

    public MktCobhDimCanal getCanalNuevo() {
        return canalNuevo;
    }

    public void setCanalNuevo(MktCobhDimCanal canalNuevo) {
        this.canalNuevo = canalNuevo;
    }

    public MktCobhDimCanal getCanalSeleccionado() {
        return canalSeleccionado;
    }

    public void setCanalSeleccionado(MktCobhDimCanal canalSeleccionado) {
        this.canalSeleccionado = canalSeleccionado;
    }

    public List<MktCobhDimCanal> getCanales() {
        return canales;
    }

    public void setCanales(List<MktCobhDimCanal> canales) {
        this.canales = canales;
    }

    public void nuevoCanal() {
        canalNuevo = new MktCobhDimCanal();
        canalSeleccionado = null;
    }

    public void selectCanal() {
        canalNuevo.setCanaltexto(canalSeleccionado.getCanaltexto());
        canalNuevo.setGvCanal(canalSeleccionado.getGvCanal());
        canalNuevo.setPkCanal(canalSeleccionado.getPkCanal());        
//        canalNuevo.setMktCobhStHisDatosList(canalSeleccionado.getMktCobhStHisDatosList());        
    }

    public void saveCanal() {
        FacesMessage message = null;
        MktCobhDimCanalDao canalDao = new MktCobhDimCanalDao();
        if (canalDao.saveCanal(canalNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Registro Guardado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al guadar el registro , " + canalDao.getError());
            canalNuevo.setPkCanal(null);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void eliminarCanal() {
        FacesMessage message = null;
        MktCobhDimCanalDao canalDao = new MktCobhDimCanalDao();
        if (canalDao.EliminarCanal(canalNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Canal eliminado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede eliminar el Canal por el siguiente error: " + canalDao.getError());
            canalNuevo = null;
        }
        refreshCanales();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void refreshCanales(){
        MktCobhDimCanalDao canalDao = new MktCobhDimCanalDao();
        canales = canalDao.getCanalesAll();
    }
    public void exportaExcelCanal() {        
        
    }
    public int sumaCanal() {
        return (int) (500);
    }
 
    //Integrando Excel
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
        XlsAnalizerCanalExcel analizer = new XlsAnalizerCanalExcel();
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

    public void handleFileUploadCatalogoExcel(FileUploadEvent event) {
        FacesMessage message = null;
        XlsAnalizerCanalExcel analizer = new XlsAnalizerCanalExcel();
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
        Boolean flagLoadMktDatos = (Boolean) context.getAttribute("BanderaDatos");
        if (!flagLoadMktDatos) {
            flagLoadMktDatos = true;
            context.setAttribute("BanderaDatos", flagLoadMktDatos);
            Record record = new Record();
            record.setFecha(new Date());
            record.setNameFile(nameFile);
            record.setProcess("Carga de Archivo de Datos Canal");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerCanalExcel salesDatos = new XlsAnalizerCanalExcel();
            if (salesDatos.saveSheetInfoDatos(uploadedFile, stream, numRegistros,numProcesados,numFaltantes)) {
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
            record.setProcess("Carga de Archivo de Datos Canales");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerCanalExcel canalDatos = new XlsAnalizerCanalExcel();
            
            if (canalDatos.saveSheetInfoDatos(uploadedFile, stream, numRegistros,numProcesados,numFaltantes)) {
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
    
    
}

