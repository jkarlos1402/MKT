package com.femsa.mkt.managedbeans;

import com.femsa.mkt.analizer.XlsAnalizerUoExcel;
import com.femsa.mkt.dao.MktCobhDimUoDao;
import com.femsa.mkt.pojos.MktCobhDimUo;
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

@ManagedBean(name = "mktCobhDimUoBean")
@SessionScoped
public class MktCobhDimUoBean {
    private MktCobhDimUo uoNuevo;
    private MktCobhDimUo uoSeleccionado;
    private List<MktCobhDimUo> uoes;
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

    public MktCobhDimUoBean() {
        MktCobhDimUoDao uoDao = new MktCobhDimUoDao();
        uoes = uoDao.getUoesAll();
        uoNuevo = new MktCobhDimUo();
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        cargas = new ArrayList<Record>();
    }

    public MktCobhDimUo getUoNuevo() {
        return uoNuevo;
    }

    public void setUoNuevo(MktCobhDimUo uoNuevo) {
        this.uoNuevo = uoNuevo;
    }

    public MktCobhDimUo getUoSeleccionado() {
        return uoSeleccionado;
    }

    public void setUoSeleccionado(MktCobhDimUo uoSeleccionado) {
        this.uoSeleccionado = uoSeleccionado;
    }

    public List<MktCobhDimUo> getUoes() {
        return uoes;
    }

    public void setUoes(List<MktCobhDimUo> uoes) {
        this.uoes = uoes;
    }

    public void nuevoUo() {
        uoNuevo = new MktCobhDimUo();
        uoSeleccionado = null;
    }

    public void selectUo() {
        uoNuevo.setUo(uoSeleccionado.getUo());
        uoNuevo.setGvUo(uoSeleccionado.getGvUo());
        uoNuevo.setPkUo(uoSeleccionado.getPkUo());        
//        uoNuevo.setMktCobhStHisDatosList(uoSeleccionado.getMktCobhStHisDatosList());        
    }

    public void saveUo() {
        FacesMessage message = null;
        MktCobhDimUoDao uoDao = new MktCobhDimUoDao();
        if (uoDao.saveUo(uoNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Registro Guardado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al guadar el registro , " + uoDao.getError());
            uoNuevo.setPkUo(null);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void eliminarUo() {
        FacesMessage message = null;
        MktCobhDimUoDao uoDao = new MktCobhDimUoDao();
        if (uoDao.EliminarUo(uoNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Uo eliminado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede eliminar el Uo por el siguiente error: " + uoDao.getError());
            uoNuevo = null;
        }
        refreshUoes();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void refreshUoes(){
        MktCobhDimUoDao uoDao = new MktCobhDimUoDao();
        uoes = uoDao.getUoesAll();
    }
    public void exportaExcelUo() {        
        
    }
    public int sumaUo() {
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
        XlsAnalizerUoExcel analizer = new XlsAnalizerUoExcel();
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
        XlsAnalizerUoExcel analizer = new XlsAnalizerUoExcel();
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
            record.setProcess("Carga de Archivo de Datos Uo");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerUoExcel salesDatos = new XlsAnalizerUoExcel();
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
            record.setProcess("Carga de Archivo de Datos Uoes");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerUoExcel uoDatos = new XlsAnalizerUoExcel();
            
            if (uoDatos.saveSheetInfoDatos(uploadedFile, stream, numRegistros,numProcesados,numFaltantes)) {
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

