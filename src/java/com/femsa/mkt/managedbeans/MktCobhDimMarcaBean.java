package com.femsa.mkt.managedbeans;

import com.femsa.mkt.analizer.XlsAnalizerMarcaExcel;
import com.femsa.mkt.dao.MktCobhDimMarcaDao;
import com.femsa.mkt.dao.MktCobhDimSegmentoDao;
import com.femsa.mkt.pojos.MktCobhDimMarca;
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
import com.femsa.mkt.pojos.MktCobhDimSegmento;


@ManagedBean(name = "mktCobhDimMarcaBean")
@SessionScoped
public class MktCobhDimMarcaBean {
    private MktCobhDimMarca marcaNuevo;
    private MktCobhDimMarca marcaSeleccionado;
    private List<MktCobhDimMarca> marcaes;
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
    private List<MktCobhDimSegmento> catSegmentos;    

    public MktCobhDimMarcaBean() {
        MktCobhDimMarcaDao marcaDao = new MktCobhDimMarcaDao();
        marcaes = marcaDao.getMarcaesAll();
        marcaNuevo = new MktCobhDimMarca();
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        cargas = new ArrayList<Record>();
        MktCobhDimSegmentoDao segmentoDao = new MktCobhDimSegmentoDao();
        catSegmentos = segmentoDao.getSegmentoesAll();
        System.out.println(catSegmentos.size());        
    }

    public List<MktCobhDimSegmento> getCatSegmentos() {
        return catSegmentos;
    }

    public void setCatSegmentos(List<MktCobhDimSegmento> catSegmentos) {
        this.catSegmentos = catSegmentos;
    }
    
    public MktCobhDimMarca getMarcaNuevo() {
        return marcaNuevo;
    }

    public void setMarcaNuevo(MktCobhDimMarca marcaNuevo) {
        this.marcaNuevo = marcaNuevo;
    }

    public MktCobhDimMarca getMarcaSeleccionado() {
        return marcaSeleccionado;
    }

    public void setMarcaSeleccionado(MktCobhDimMarca marcaSeleccionado) {
        this.marcaSeleccionado = marcaSeleccionado;
    }

    public List<MktCobhDimMarca> getMarcaes() {
        return marcaes;
    }

    public void setMarcaes(List<MktCobhDimMarca> marcaes) {
        this.marcaes = marcaes;
    }

    public void nuevoMarca() {
        marcaNuevo = new MktCobhDimMarca();
        marcaSeleccionado = null;
    }

    public void selectMarca() {

        marcaNuevo.setMarcaTexto(marcaSeleccionado.getMarcaTexto());
        marcaNuevo.setGvMarca(marcaSeleccionado.getGvMarca());
        marcaNuevo.setPkMarca(marcaSeleccionado.getPkMarca());        
//        marcaNuevo.setFkseSegmento(marcaSeleccionado.getFkseSegmento());        

    }

    public void saveMarca() {
        FacesMessage message = null;
        MktCobhDimMarcaDao marcaDao = new MktCobhDimMarcaDao();
        if (marcaDao.saveMarca(marcaNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Registro Guardado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al guadar el registro , " + marcaDao.getError());
            marcaNuevo.setPkMarca(null);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void eliminarMarca() {
        FacesMessage message = null;
        MktCobhDimMarcaDao marcaDao = new MktCobhDimMarcaDao();
        if (marcaDao.EliminarMarca(marcaNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Marca eliminado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede eliminar el Marca por el siguiente error: " + marcaDao.getError());
            marcaNuevo = null;
        }
        refreshMarcaes();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }    
    public void refreshMarcaes(){
        MktCobhDimMarcaDao marcaDao = new MktCobhDimMarcaDao();
        marcaes = marcaDao.getMarcaesAll();
    }
    public void refreshSegmentos() {
        MktCobhDimSegmentoDao segmentoDao = new MktCobhDimSegmentoDao();
        catSegmentos = segmentoDao.getSegmentoesAll();
    }    
    public void exportaExcelMarca() {        
        
    }
    public int sumaMarca() {
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
        XlsAnalizerMarcaExcel analizer = new XlsAnalizerMarcaExcel();
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
        XlsAnalizerMarcaExcel analizer = new XlsAnalizerMarcaExcel();
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
            record.setProcess("Carga de Archivo de Datos Marca");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerMarcaExcel salesDatos = new XlsAnalizerMarcaExcel();
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
            record.setProcess("Carga de Archivo de Datos Marcaes");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerMarcaExcel marcaDatos = new XlsAnalizerMarcaExcel();
            
            if (marcaDatos.saveSheetInfoDatos(uploadedFile, stream, numRegistros,numProcesados,numFaltantes)) {
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

