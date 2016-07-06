package com.femsa.mkt.managedbeans;

import com.femsa.mkt.analizer.XlsAnalizerRegionExcel;
import com.femsa.mkt.dao.MktCobhDimRegionDao;
import com.femsa.mkt.pojos.MktCobhDimRegion;

import com.femsa.mkt.dao.MktCobhDimEstadoDao;
import com.femsa.mkt.pojos.MktCobhDimEstado;

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

@ManagedBean(name = "mktCobhDimRegionBean")
@SessionScoped
public class MktCobhDimRegionBean {
    private MktCobhDimRegion regionNuevo;
    private MktCobhDimRegion regionSeleccionado;
    private List<MktCobhDimRegion> regiones;
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
    
    private List<MktCobhDimEstado> catEstados;    

    public MktCobhDimRegionBean() {
        MktCobhDimRegionDao regionDao = new MktCobhDimRegionDao();
        regiones = regionDao.getRegionesAll();
        regionNuevo = new MktCobhDimRegion();
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        cargas = new ArrayList<Record>();
        MktCobhDimEstadoDao estadoDao = new MktCobhDimEstadoDao();
        catEstados = estadoDao.getEstadoAll();
        System.out.println(catEstados.size());                
        
    }

    public List<MktCobhDimEstado> getCatEstados() {
        return catEstados;
    }

    public void setCatEstados(List<MktCobhDimEstado> catEstados) {
        this.catEstados = catEstados;
    }
    
    
    public MktCobhDimRegion getRegionNuevo() {
        return regionNuevo;
    }

    public void setRegionNuevo(MktCobhDimRegion regionNuevo) {
        this.regionNuevo = regionNuevo;
    }

    public MktCobhDimRegion getRegionSeleccionado() {
        return regionSeleccionado;
    }

    public void setRegionSeleccionado(MktCobhDimRegion regionSeleccionado) {
        this.regionSeleccionado = regionSeleccionado;
    }

    public List<MktCobhDimRegion> getRegiones() {
        return regiones;
    }

    public void setRegiones(List<MktCobhDimRegion> regiones) {
        this.regiones = regiones;
    }

    public void nuevoRegion() {
        regionNuevo = new MktCobhDimRegion();
        regionSeleccionado = null;
    }

    public void selectRegion() {
        regionNuevo.setRegion(regionSeleccionado.getRegion());
        regionNuevo.setGvRegion(regionSeleccionado.getGvRegion());
        regionNuevo.setPkRegion(regionSeleccionado.getPkRegion());        
        regionNuevo.setFkEstado(regionSeleccionado.getFkEstado());    
        
    }

    public void saveRegion() {
        FacesMessage message = null;
        MktCobhDimRegionDao regionDao = new MktCobhDimRegionDao();
        if (regionDao.saveRegion(regionNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Registro Guardado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al guadar el registro , " + regionDao.getError());
            regionNuevo.setPkRegion(null);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void eliminarRegion() {
        FacesMessage message = null;
        MktCobhDimRegionDao regionDao = new MktCobhDimRegionDao();
        if (regionDao.EliminarRegion(regionNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Region eliminado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede eliminar el Region por el siguiente error: " + regionDao.getError());
            regionNuevo = null;
        }
        refreshRegiones();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void refreshRegiones(){
        MktCobhDimRegionDao regionDao = new MktCobhDimRegionDao();
        regiones = regionDao.getRegionesAll();
    }
    public void exportaExcelRegion() {        
        
    }
    public int sumaRegion() {
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
        XlsAnalizerRegionExcel analizer = new XlsAnalizerRegionExcel();
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
        XlsAnalizerRegionExcel analizer = new XlsAnalizerRegionExcel();
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
            record.setProcess("Carga de Archivo de Datos Region");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerRegionExcel salesDatos = new XlsAnalizerRegionExcel();
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
            record.setProcess("Carga de Archivo de Datos Regiones");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerRegionExcel regionDatos = new XlsAnalizerRegionExcel();
            
            if (regionDatos.saveSheetInfoDatos(uploadedFile, stream, numRegistros,numProcesados,numFaltantes)) {
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

