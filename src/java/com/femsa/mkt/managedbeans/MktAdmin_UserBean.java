package com.femsa.mkt.managedbeans;

import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.DataBaseException;
import com.femsa.mkt.pojos.MktAdmin_CatProyecto;
import com.femsa.mkt.pojos.MktAdmin_CatRol;
import com.femsa.mkt.pojos.MktAdmin_Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author TMXIDSJPINAM
 */
@ManagedBean(name = "userBean")
@ViewScoped
public class MktAdmin_UserBean implements Serializable {

    private MktAdmin_Usuario usuarioNuevo = new MktAdmin_Usuario();
    private MktAdmin_Usuario usuarioSelected;

    private List<MktAdmin_Usuario> usuariosAll;

    private List<MktAdmin_CatRol> catRoles = new ArrayList<MktAdmin_CatRol>();
//    private MktAdmin_CatRol rolSelected;

    private List<MktAdmin_CatProyecto> catProyectos = new ArrayList<MktAdmin_CatProyecto>();
    private MktAdmin_CatProyecto proyectoSelected;

    private String error;

    /**
     *
     */
    public MktAdmin_UserBean() {
        startBean();
    }

    private void startBean() {
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String errorDatabase = (String) context.getAttribute("error_database");
        if (errorDatabase == null || ("".equalsIgnoreCase(errorDatabase.trim()))) {
            GenericDAO genericDAO = null;
            try {
                genericDAO = new GenericDAO();
                catRoles = genericDAO.findAll(MktAdmin_CatRol.class);
                catProyectos = genericDAO.findByComponent(MktAdmin_CatProyecto.class, "status", true);
                usuariosAll = genericDAO.findAll(MktAdmin_Usuario.class);
            } catch (DataBaseException ex) {
                Logger.getLogger(MktAdmin_UserBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DAOException ex) {
                Logger.getLogger(MktAdmin_UserBean.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (genericDAO != null) {
                    genericDAO.closeDAO();
                }
            }
        } else {
            error = "No database connection - " + errorDatabase;
        }
    }

    /**
     *
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     *
     * @return
     */
    public List<MktAdmin_CatProyecto> getCatProyectos() {
        return catProyectos;
    }

    /**
     *
     * @param catProyectos
     */
    public void setCatProyectos(List<MktAdmin_CatProyecto> catProyectos) {
        this.catProyectos = catProyectos;
    }

    /**
     *
     * @return
     */
    public MktAdmin_CatProyecto getProyectoSelected() {
        return proyectoSelected;
    }

    /**
     *
     * @param proyectoSelected
     */
    public void setProyectoSelected(MktAdmin_CatProyecto proyectoSelected) {
        this.proyectoSelected = proyectoSelected;
    }

    /**
     *
     * @return
     */
    public List<MktAdmin_CatRol> getCatRoles() {
        return catRoles;
    }

    /**
     *
     * @param catRoles
     */
    public void setCatRoles(List<MktAdmin_CatRol> catRoles) {
        this.catRoles = catRoles;
    }

    /**
     *
     * @return
     */
    public MktAdmin_Usuario getUsuarioNuevo() {
        return usuarioNuevo;
    }

    /**
     *
     * @param usuarioNuevo
     */
    public void setUsuarioNuevo(MktAdmin_Usuario usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

    /**
     *
     * @return
     */
    public MktAdmin_Usuario getUsuarioSelected() {
        return usuarioSelected;
    }

    /**
     *
     * @param usuarioSelected
     */
    public void setUsuarioSelected(MktAdmin_Usuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    /**
     *
     * @return
     */
    public List<MktAdmin_Usuario> getUsuariosAll() {
        return usuariosAll;
    }

    /**
     *
     * @param usuariosAll
     */
    public void setUsuariosAll(List<MktAdmin_Usuario> usuariosAll) {
        this.usuariosAll = usuariosAll;
    }

    /**
     *
     */
    public void saveUser() {
        FacesMessage message;
        GenericDAO genericDAO = null;
        try {
            genericDAO = new GenericDAO();
            if (usuarioNuevo.getProyectos() == null) {
                usuarioNuevo.setProyectos(new ArrayList<MktAdmin_CatProyecto>());
            } else {
                usuarioNuevo.getProyectos().clear();
            }
            usuarioNuevo.setPassword(usuarioNuevo.getPassword());
            usuarioNuevo.getProyectos().add(proyectoSelected);
            if (usuarioNuevo.getIntentos() != null && usuarioNuevo.getIntentos() == 3) {
                usuarioNuevo.setPassreset(true);
            }
            usuarioNuevo.setIntentos(0);
            genericDAO.saveOrUpdate(usuarioNuevo);
            refreshUsers();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Terminado", "Usuario guardado");
        } catch (DataBaseException ex) {
            usuarioNuevo.setPkUsuario(null);
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            Logger.getLogger(MktAdmin_UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            usuarioNuevo.setPkUsuario(null);
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            Logger.getLogger(MktAdmin_UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *
     */
    public void newUser() {
        usuarioNuevo = new MktAdmin_Usuario();
        usuarioSelected = null;
        proyectoSelected = null;
    }

    /**
     *
     */
    public void selectUser() {
        usuarioNuevo.setPkUsuario(usuarioSelected.getPkUsuario());
        usuarioNuevo.setEstatus(usuarioSelected.getEstatus());
        usuarioNuevo.setMail(usuarioSelected.getMail());
        usuarioNuevo.setNombre(usuarioSelected.getNombre());
        usuarioNuevo.setPassword(usuarioSelected.getPassword());
        usuarioNuevo.setRol(usuarioSelected.getRol());
        usuarioNuevo.setUsuario(usuarioSelected.getUsuario());
        if (usuarioNuevo.getProyectos() != null && !usuarioNuevo.getProyectos().isEmpty()) {
            usuarioNuevo.getProyectos().clear();
        }
        if (usuarioSelected.getProyectos() != null && !usuarioSelected.getProyectos().isEmpty()) {
            proyectoSelected = usuarioSelected.getProyectos().get(0);
            for (MktAdmin_CatProyecto proyecto : usuarioSelected.getProyectos()) {
                usuarioNuevo.getProyectos().add(proyecto);
            }
        }
        usuarioNuevo.setIntentos(usuarioSelected.getIntentos());
        usuarioNuevo.setLastLogin(usuarioSelected.getLastLogin());

    }

    public void deleteUser() {
        FacesMessage message;
        GenericDAO genericDAO = null;
        try {
            genericDAO = new GenericDAO();
            genericDAO.delete(usuarioNuevo);
            refreshUsers();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Usuario eliminado");
        } catch (DataBaseException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            Logger.getLogger(MktRolBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            Logger.getLogger(MktRolBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *
     */
    public void refreshUsers() {
        GenericDAO genericDAO = null;
        try {
            genericDAO = new GenericDAO();
            usuariosAll = genericDAO.findAll(MktAdmin_Usuario.class);
        } catch (DataBaseException ex) {
            Logger.getLogger(MktAdmin_UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(MktAdmin_UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
    }
}
