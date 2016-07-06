package com.femsa.mkt.managedbeans;

import com.femsa.mkt.analizer.XlsAnalizerSegmentoExcel;
import com.femsa.mkt.dao.MktCobhDimSegmentoDao;
import com.femsa.mkt.pojos.MktCobhDimSegmento;
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

@ManagedBean(name = "mktCobhDimSegmentoBean")
@SessionScoped
public class MktCobhDimSegmentoBean {

    private MktCobhDimSegmento segmentoNuevo;
    private MktCobhDimSegmento segmentoSeleccionado;
    private List<MktCobhDimSegmento> segmentoes;
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

    public MktCobhDimSegmentoBean() {
        MktCobhDimSegmentoDao segmentoDao = new MktCobhDimSegmentoDao();
        segmentoes = segmentoDao.getSegmentoesAll();
        segmentoNuevo = new MktCobhDimSegmento();
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        cargas = new ArrayList<Record>();
    }

    public MktCobhDimSegmento getSegmentoNuevo() {
        return segmentoNuevo;
    }

    public void setSegmentoNuevo(MktCobhDimSegmento segmentoNuevo) {
        this.segmentoNuevo = segmentoNuevo;
    }

    public MktCobhDimSegmento getSegmentoSeleccionado() {
        return segmentoSeleccionado;
    }

    public void setSegmentoSeleccionado(MktCobhDimSegmento segmentoSeleccionado) {
        this.segmentoSeleccionado = segmentoSeleccionado;
    }

    public List<MktCobhDimSegmento> getSegmentoes() {
        return segmentoes;
    }

    public void setSegmentoes(List<MktCobhDimSegmento> segmentoes) {
        this.segmentoes = segmentoes;
    }

    public void nuevoSegmento() {
        segmentoNuevo = new MktCobhDimSegmento();
        segmentoSeleccionado = null;
    }

    public void selectSegmento() {
        segmentoNuevo.setSegmentoTexto(segmentoSeleccionado.getSegmentoTexto());
        segmentoNuevo.setGvSegmento(segmentoSeleccionado.getGvSegmento());
        segmentoNuevo.setPkSegmento(segmentoSeleccionado.getPkSegmento());
//        segmentoNuevo.setMktCobhStHisDatosList(segmentoSeleccionado.getMktCobhStHisDatosList());        
    }

    public void saveSegmento() {
        FacesMessage message;
        MktCobhDimSegmentoDao segmentoDao = new MktCobhDimSegmentoDao();
        if (segmentoDao.saveSegmento(segmentoNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Registro Guardado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al guadar el registro , " + segmentoDao.getError());
            segmentoNuevo.setPkSegmento(null);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        refreshSegmentoes();
    }

    public void eliminarSegmento() {
        FacesMessage message = null;
        MktCobhDimSegmentoDao segmentoDao = new MktCobhDimSegmentoDao();
        if (segmentoDao.EliminarSegmento(segmentoNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Segmento eliminado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede eliminar el Segmento por el siguiente error: " + segmentoDao.getError());
//            segmentoNuevo = null;
            segmentoNuevo.setPkSegmento(null);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        refreshSegmentoes();

    }

    public void refreshSegmentoes() {
        MktCobhDimSegmentoDao segmentoDao = new MktCobhDimSegmentoDao();
        segmentoes = segmentoDao.getSegmentoesAll();
    }

    public void exportaExcelSegmento() {

    }

    public int sumaSegmento() {
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
        XlsAnalizerSegmentoExcel analizer = new XlsAnalizerSegmentoExcel();
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
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", event.getFile().getFileName() + " Archivo Cargado - a " + analizer.getLactualizado() + " c - " + analizer.getLcreados() + " e -" + analizer.getLerror() );
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", event.getFile().getFileName() + " Error en el Archivo");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void handleFileUploadCatalogoExcel(FileUploadEvent event) {
        FacesMessage message = null;
        XlsAnalizerSegmentoExcel analizer = new XlsAnalizerSegmentoExcel();
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
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completado", event.getFile().getFileName() + " Archivo Cargado - a " + analizer.getLactualizado() + " c - " + analizer.getLcreados() + " e -" + analizer.getLerror());
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
            record.setProcess("Carga de Archivo de Datos Segmento");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerSegmentoExcel salesDatos = new XlsAnalizerSegmentoExcel();
            if (salesDatos.saveSheetInfoDatos(uploadedFile, stream, numRegistros, numProcesados, numFaltantes)) {
                record.setNumEntriesSaved(numRegistros);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registros Cargados" + salesDatos.getTcreados() + " - " + salesDatos.getTactualizado() + " - " + salesDatos.getTeliminado() + " - " + salesDatos.getTerror());
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
            record.setProcess("Carga de Archivo de Datos Segmentoes");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerSegmentoExcel segmentoDatos = new XlsAnalizerSegmentoExcel();

            if (segmentoDatos.saveSheetInfoDatos(uploadedFile, stream, numRegistros, numProcesados, numFaltantes)) {
                record.setNumEntriesSaved(numRegistros);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registros Cargados" + segmentoDatos.getTcreados() + " - " + segmentoDatos.getTactualizado() + " - " + segmentoDatos.getTeliminado() + " - " + segmentoDatos.getTerror());
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
