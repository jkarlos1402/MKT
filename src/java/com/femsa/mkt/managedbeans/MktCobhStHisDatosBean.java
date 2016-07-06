package com.femsa.mkt.managedbeans;

import com.femsa.mkt.dao.MktCobhStHisDatosDao;
import com.femsa.mkt.pojos.MktCobhStHisDatos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "mktCobhStHisDatosBean")
@SessionScoped
public class MktCobhStHisDatosBean implements Serializable {

    private MktCobhStHisDatos HisDatosNuevo;
    private MktCobhStHisDatos HisDatosSeleccionado;

    private List<MktCobhStHisDatos> HisDatoss;

    public MktCobhStHisDatosBean() {
        MktCobhStHisDatosDao HisDatosDao = new MktCobhStHisDatosDao();
        HisDatoss = HisDatosDao.getHisDatosAll();
        HisDatosNuevo = new MktCobhStHisDatos();
    }

    public MktCobhStHisDatos getHisDatosNuevo() {
        return HisDatosNuevo;
    }

    public void setHisDatosNuevo(MktCobhStHisDatos HisDatosNuevo) {
        this.HisDatosNuevo = HisDatosNuevo;
    }

    public MktCobhStHisDatos getHisDatosSeleccionado() {
        return HisDatosSeleccionado;
    }

    public void setHisDatosSeleccionado(MktCobhStHisDatos HisDatosSeleccionado) {
        this.HisDatosSeleccionado = HisDatosSeleccionado;
    }

    public List<MktCobhStHisDatos> getHisDatoss() {
        return HisDatoss;
    }

    public void setHisDatoss(List<MktCobhStHisDatos> HisDatoss) {
        this.HisDatoss = HisDatoss;
    }

    public void nuevoHisDatos() {
        HisDatosNuevo = new MktCobhStHisDatos();
        HisDatosSeleccionado = null;
    }

    public void selectHisDatos() {
        
        
        HisDatosNuevo.setIddatos(HisDatosSeleccionado.getIddatos());
        HisDatosNuevo.setIdcanal(HisDatosSeleccionado.getIdcanal());
        HisDatosNuevo.setIdestado(HisDatosSeleccionado.getIdestado());
        HisDatosNuevo.setIdregion(HisDatosSeleccionado.getIdregion());
        HisDatosNuevo.setIduo(HisDatosSeleccionado.getIduo());        
        HisDatosNuevo.setIdgec(HisDatosSeleccionado.getIdgec());        
        HisDatosNuevo.setIdsegmento(HisDatosSeleccionado.getIdsegmento());
        HisDatosNuevo.setIdmarca(HisDatosSeleccionado.getIdmarca());
        HisDatosNuevo.setIdempaque(HisDatosSeleccionado.getIdempaque());
        HisDatosNuevo.setFecha(HisDatosSeleccionado.getFecha());
        HisDatosNuevo.setIncluir(HisDatosSeleccionado.getIncluir());
        HisDatosNuevo.setCoberturaObjetivo(HisDatosSeleccionado.getCoberturaObjetivo());
        HisDatosNuevo.setClientesObjetivo(HisDatosSeleccionado.getClientesObjetivo());
        HisDatosNuevo.setClientesActivos(HisDatosSeleccionado.getClientesActivos());
        HisDatosNuevo.setClientesVenta(HisDatosSeleccionado.getClientesVenta());        
        //HisDatosNuevo.setMktCobhStHisDatosList(HisDatosSeleccionado.getMktCobhStHisDatosList());
    }

    public void saveHisDatos() {
        FacesMessage message = null;
        MktCobhStHisDatosDao HisDatosDao = new MktCobhStHisDatosDao();
        if (HisDatosDao.saveHisDatos(HisDatosNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Registro Guardado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al guadar el registro , " + HisDatosDao.getError());
            HisDatosNuevo.setIddatos(null);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void eliminarHisDatos() {
        FacesMessage message = null;
        MktCobhStHisDatosDao HisDatosDao = new MktCobhStHisDatosDao();
        if (HisDatosDao.EliminarHisDatos(HisDatosNuevo)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "HisDatos eliminado");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se puede eliminar el HisDatos por el siguiente error: " + HisDatosDao.getError());
            HisDatosNuevo = null;
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
