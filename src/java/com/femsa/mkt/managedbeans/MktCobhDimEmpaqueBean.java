package com.femsa.mkt.managedbeans;

import com.femsa.mkt.analizer.XlsAnalizerEmpaqueExcel;
import com.femsa.mkt.dao.MktCobhDimEmpaqueDao;
import com.femsa.mkt.pojos.MktCobhDimEmpaque;

import com.femsa.mkt.dao.MktCobhDimSegmentoDao;
import com.femsa.mkt.pojos.MktCobhDimSegmento;

import com.femsa.mkt.dao.MktCobhDimMarcaDao;
import com.femsa.mkt.pojos.MktCobhDimMarca;

import com.femsa.mkt.util.Record;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "mktCobhDimEmpaqueBean")
@SessionScoped
public class MktCobhDimEmpaqueBean {

    private MktCobhDimEmpaque empaqueNuevo;
    private MktCobhDimEmpaque empaqueSeleccionado;
    private List<MktCobhDimEmpaque> empaquees;
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
    private List<MktCobhDimMarca> catMarcas;
    
    public MktCobhDimEmpaqueBean() {
        MktCobhDimEmpaqueDao empaqueDao = new MktCobhDimEmpaqueDao();
        empaquees = empaqueDao.getEmpaqueesAll();
        empaqueNuevo = new MktCobhDimEmpaque();
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        cargas = new ArrayList<Record>();
        
        MktCobhDimSegmentoDao segmentoDao = new MktCobhDimSegmentoDao();        
        catSegmentos = segmentoDao.getSegmentoesAll();
        
        MktCobhDimMarcaDao marcaDao = new MktCobhDimMarcaDao();        
        catMarcas = marcaDao.getMarcaesAll();
        
    }

    public void refreshSegmentos() {
        MktCobhDimSegmentoDao segmentoDao = new MktCobhDimSegmentoDao();
        catSegmentos = segmentoDao.getSegmentoesAll();
    }
    public List<MktCobhDimSegmento> getCatSegmentos() {
        return catSegmentos;
    }
    public void setCatSegmentos(List<MktCobhDimSegmento> catSegmentos) {
        this.catSegmentos = catSegmentos;
    }

    public void refreshMarcas() {
        MktCobhDimMarcaDao marcaDao = new MktCobhDimMarcaDao();
        catMarcas = marcaDao.getMarcaesAll();
    }    

    public List<MktCobhDimMarca> getCatMarcas() {
        return catMarcas;
    }

    public void setCatMarcas(List<MktCobhDimMarca> catMarcas) {
        this.catMarcas = catMarcas;
    }
    
    public MktCobhDimEmpaque getEmpaqueNuevo() {
        return empaqueNuevo;
    }

    public void setEmpaqueNuevo(MktCobhDimEmpaque empaqueNuevo) {
        this.empaqueNuevo = empaqueNuevo;
    }

    public MktCobhDimEmpaque getEmpaqueSeleccionado() {
        return empaqueSeleccionado;
    }

    public void setEmpaqueSeleccionado(MktCobhDimEmpaque empaqueSeleccionado) {
        this.empaqueSeleccionado = empaqueSeleccionado;
    }

    public List<MktCobhDimEmpaque> getEmpaquees() {
        return empaquees;
    }

    public void setEmpaquees(List<MktCobhDimEmpaque> empaquees) {
        this.empaquees = empaquees;
    }

    public void nuevoEmpaque() {
        empaqueNuevo = new MktCobhDimEmpaque();
        empaqueSeleccionado = null;
    }

    public void selectEmpaque() {
//        empaqueNuevo.setEmpaque(empaqueSeleccionado.getEmpaque());
        empaqueNuevo.setGvEmpaque(empaqueSeleccionado.getGvEmpaque());
        empaqueNuevo.setPkEmpaque(empaqueSeleccionado.getPkEmpaque());

//        empaqueNuevo.setFkMarca(empaqueSeleccionado.getFkMarca());
//        empaqueNuevo.setFkseSegmento(empaqueSeleccionado.getFkseSegmento());

//        empaqueNuevo.setMktCobhStHisDatosList(empaqueSeleccionado.getMktCobhStHisDatosList());        
    }

    public void saveEmpaque() {
        FacesMessage message = null;
        MktCobhDimEmpaqueDao empaqueDao = new MktCobhDimEmpaqueDao();
        if (empaqueDao.saveEmpaque(empaqueNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Registro Guardado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al guadar el registro , " + empaqueDao.getError());
            empaqueNuevo.setPkEmpaque(null);
        }
        refreshEmpaquees();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void eliminarEmpaque() {
        FacesMessage message = null;
        MktCobhDimEmpaqueDao empaqueDao = new MktCobhDimEmpaqueDao();
        if (empaqueDao.EliminarEmpaque(empaqueNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Empaque eliminado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede eliminar el Empaque por el siguiente error: " + empaqueDao.getError());
            empaqueNuevo = null;
        }
        refreshEmpaquees();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void refreshEmpaquees() {
        MktCobhDimEmpaqueDao empaqueDao = new MktCobhDimEmpaqueDao();
        empaquees = empaqueDao.getEmpaqueesAll();
    }

    public void exportaExcelEmpaque() {

    }

    public int sumaEmpaque() {
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
        XlsAnalizerEmpaqueExcel analizer = new XlsAnalizerEmpaqueExcel();
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
        XlsAnalizerEmpaqueExcel analizer = new XlsAnalizerEmpaqueExcel();
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
            record.setProcess("Carga de Archivo de Datos Empaque");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerEmpaqueExcel salesDatos = new XlsAnalizerEmpaqueExcel();
            if (salesDatos.saveSheetInfoDatos(uploadedFile, stream, numRegistros, numProcesados, numFaltantes)) {
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
            record.setProcess("Carga de Archivo de Datos Empaquees");
            record.setDateExecution(new Date());
            record.setProject("MARKETING");
            XlsAnalizerEmpaqueExcel empaqueDatos = new XlsAnalizerEmpaqueExcel();

            if (empaqueDatos.saveSheetInfoDatos(uploadedFile, stream, numRegistros, numProcesados, numFaltantes)) {
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
        refreshEmpaquees();
    }

    public void listener() {
//        System.out.println("entro");
//        for (MktCobhDimMarca marca : empaqueNuevo.getFkseSegmento().getMarcas()) {
//            System.out.println(marca);
//        }
    }
}
