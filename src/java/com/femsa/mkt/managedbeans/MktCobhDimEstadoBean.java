package com.femsa.mkt.managedbeans;

import com.femsa.mkt.dao.MktCobhDimEstadoDao;
import com.femsa.mkt.pojos.MktCobhDimEstado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "mktCobhDimEstadoBean")
@SessionScoped
public class MktCobhDimEstadoBean implements Serializable {

    private MktCobhDimEstado estadoNuevo;
    private MktCobhDimEstado estadoSeleccionado;

    private List<MktCobhDimEstado> estados;

    public MktCobhDimEstadoBean() {
        MktCobhDimEstadoDao estadoDao = new MktCobhDimEstadoDao();
        estados = estadoDao.getEstadoAll();
        estadoNuevo = new MktCobhDimEstado();
    }

    public MktCobhDimEstado getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(MktCobhDimEstado estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public MktCobhDimEstado getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(MktCobhDimEstado estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public List<MktCobhDimEstado> getEstados() {
        return estados;
    }

    public void setEstados(List<MktCobhDimEstado> estados) {
        this.estados = estados;
    }

    public void nuevoEstado() {
        estadoNuevo = new MktCobhDimEstado();
        estadoSeleccionado = null;
    }

    public void selectEstado() {
        estadoNuevo.setEstado(estadoSeleccionado.getEstado());
        estadoNuevo.setGvEstado(estadoSeleccionado.getGvEstado());
        estadoNuevo.setPkEstado(estadoSeleccionado.getPkEstado());
//        estadoNuevo.setMktCobhDimRegionList(estadoSeleccionado.getMktCobhDimRegionList());
//        estadoNuevo.setMktCobhStHisDatosList(estadoSeleccionado.getMktCobhStHisDatosList());
    }

    public void saveEstado() {
        FacesMessage message = null;
        MktCobhDimEstadoDao estadoDao = new MktCobhDimEstadoDao();
        System.out.println("paso2" + estadoNuevo);
        if (estadoDao.saveEstado(estadoNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Registro Guardado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al guadar el registro , " + estadoDao.getError());
            estadoNuevo.setPkEstado(null);
        }
        refreshEstados();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void eliminarEstado() {
        FacesMessage message = null;
        MktCobhDimEstadoDao estadoDao = new MktCobhDimEstadoDao();
        if (estadoDao.EliminarEstado(estadoNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Estado eliminado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede eliminar el estado por el siguiente error: " + estadoDao.getError());
            estadoNuevo = null;
        }
        refreshEstados();
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void refreshEstados(){
        MktCobhDimEstadoDao estadoDao = new MktCobhDimEstadoDao();
        estados = estadoDao.getEstadoAll();
    }
    public void exportaExcelEstado() {        
        
    }
    public int sumaEstado() {
        return (int) (500);
    }
   
}
