package com.femsa.mkt.managedbeans;

import com.femsa.mkt.analizer.XlsAnalizerGecExcel;
import com.femsa.mkt.dao.MktCobhDimGecDao;
import com.femsa.mkt.pojos.MktCobhDimGec;
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

@ManagedBean(name = "mktCobhDimGecBean")
@SessionScoped
public class MktCobhDimGecBean {
    private MktCobhDimGec gecNuevo;
    private MktCobhDimGec gecSeleccionado;
    private List<MktCobhDimGec> gecs;
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

    public MktCobhDimGecBean() {
        MktCobhDimGecDao gecDao = new MktCobhDimGecDao();
        gecs = gecDao.getGecsAll();
        gecNuevo = new MktCobhDimGec();
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        cargas = new ArrayList<Record>();
    }

    public MktCobhDimGec getGecNuevo() {
        return gecNuevo;
    }

    public void setGecNuevo(MktCobhDimGec gecNuevo) {
        this.gecNuevo = gecNuevo;
    }

    public MktCobhDimGec getGecSeleccionado() {
        return gecSeleccionado;
    }

    public void setGecSeleccionado(MktCobhDimGec gecSeleccionado) {
        this.gecSeleccionado = gecSeleccionado;
    }

    public List<MktCobhDimGec> getGecs() {
        return gecs;
    }

    public void setGecs(List<MktCobhDimGec> gecs) {
        this.gecs = gecs;
    }

    public void nuevoGec() {
        gecNuevo = new MktCobhDimGec();
        gecSeleccionado = null;
    }

    public void selectGec() {
        gecNuevo.setGecTexto(gecSeleccionado.getGecTexto());
        gecNuevo.setGvGec(gecSeleccionado.getGvGec());
        gecNuevo.setPkGec(gecSeleccionado.getPkGec());        
//        gecNuevo.setMktCobhStHisDatosList(gecSeleccionado.getMktCobhStHisDatosList());        
    }

    public void saveGec() {
        FacesMessage message = null;
        MktCobhDimGecDao gecDao = new MktCobhDimGecDao();
        if (gecDao.saveGec(gecNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Registro Guardado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al guadar el registro , " + gecDao.getError());
            gecNuevo.setPkGec(null);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void eliminarGec() {
        FacesMessage message = null;
        MktCobhDimGecDao gecDao = new MktCobhDimGecDao();
        if (gecDao.EliminarGec(gecNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Gec eliminado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede eliminar el Gec por el siguiente error: " + gecDao.getError());
            gecNuevo = null;
        }
        refreshGeces();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void refreshGeces(){
        MktCobhDimGecDao gecDao = new MktCobhDimGecDao();
        gecs = gecDao.getGecsAll();
    }
    public void exportaExcelGec() {        
        
    }
    public int sumaGec() {
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
        XlsAnalizerGecExcel analizer = new XlsAnalizerGecExcel();
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
        XlsAnalizerGecExcel analizer = new XlsAnalizerGecExcel();
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
            record.setProcess("Carga de Archivo de Datos Gec");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerGecExcel salesDatos = new XlsAnalizerGecExcel();
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
            record.setProcess("Carga de Archivo de Datos Geces");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerGecExcel gecDatos = new XlsAnalizerGecExcel();
            
            if (gecDatos.saveSheetInfoDatos(uploadedFile, stream, numRegistros,numProcesados,numFaltantes)) {
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

