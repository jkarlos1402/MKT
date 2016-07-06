package com.femsa.mkt.managedbeans;

import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.util.MktAdmin_CheckCatalogs;
import com.femsa.mkt.dao.MktAdmin_UsuarioDAO;
import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.DataBaseException;
import com.femsa.mkt.pojos.MktAdmin_Usuario;
import com.femsa.mkt.pojos.MktCatMenu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TMXIDSJPINAM
 */
@ManagedBean(name = "mainBean")
@SessionScoped
public class MktAdmin_MainBean implements Serializable {

    private String page = "/WEB-INF/pages/welcome.xhtml";
    private String pageVentana = "/WEB-INF/pages/vaciaNoBorrar.xhtml";
    private String catalog = "";
    private MktAdmin_Usuario usuario;
    private List<String> notifications;
    private String password;
    private boolean firstSession;
    private Integer porcentajeAvance = 0;
    private Long numRegistrosProcesados = 0L;
    private Long numRegistrosTotales = 0L;
    private List<MktCatMenu> menuAll = new ArrayList<MktCatMenu>();

    public MktAdmin_MainBean() {
        GenericDAO genericDAO = null;
        try {
            genericDAO = new GenericDAO();
            menuAll = genericDAO.findByQuery(MktCatMenu.class, "select m from MktCatMenu m where m.idpadre is null and m.idstatus = 1 order by m.idorden asc");
        } catch (DataBaseException ex) {
            Logger.getLogger(MktAdmin_MainBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(MktAdmin_MainBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
    }

    public List<MktCatMenu> getMenuAll() {
        return menuAll;
    }

    public void setMenuAll(List<MktCatMenu> menuAll) {
        this.menuAll = menuAll;
    }

    public String getPageVentana() {
        return pageVentana;
    }

    public void setPageVentana(String pageVentana) {
        this.pageVentana = pageVentana != null && !"".equals(pageVentana) ? pageVentana : "/WEB-INF/pages/vaciaNoBorrar.xhtml";
    }

    public Long getNumRegistrosTotales() {
        return numRegistrosTotales;
    }

    public void setNumRegistrosTotales(Long numRegistrosTotales) {
        this.numRegistrosTotales = numRegistrosTotales;
    }

    public Integer getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(Integer porcentajeAvance) {
        if (porcentajeAvance != null && porcentajeAvance > 100) {
            porcentajeAvance = 100;
        }
        this.porcentajeAvance = porcentajeAvance;
    }

    public Long getNumRegistrosProcesados() {
        return numRegistrosProcesados;
    }

    public void setNumRegistrosProcesados(Long numRegistrosProcesados) {
        this.numRegistrosProcesados = numRegistrosProcesados;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public boolean isFirstSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        firstSession = (Boolean) session.getAttribute("first_session_user");
        return firstSession;
    }

    /**
     *
     * @param firstSession
     */
    public void setFirstSession(boolean firstSession) {
        this.firstSession = firstSession;
    }

    /**
     *
     * @return
     */
    public List<String> getNotifications() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        notifications = (List<String>) session.getAttribute("notifications_user");
        return notifications;
    }

    /**
     *
     * @param notifications
     */
    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
    }

    /**
     *
     * @return
     */
    public MktAdmin_Usuario getUsuario() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (MktAdmin_Usuario) httpSession.getAttribute("session_user");
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(MktAdmin_Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     */
    public String getPage() {
        return page;
    }

    /**
     *
     * @param page
     * @param ruta
     */
    public void setPage(String page, String ruta) {
        if (!"".equalsIgnoreCase(ruta != null ? ruta : "") && !"(null)".equalsIgnoreCase(ruta != null ? ruta : "")) {
            this.page = "/WEB-INF/pages/" + ruta + "/" + page + ".xhtml";
        } else {
            this.page = "/WEB-INF/pages/" + page + ".xhtml";
        }       
    }

    public void setPage(String ruta) {
        if (!"".equalsIgnoreCase(ruta != null ? ruta : "") && !"(null)".equalsIgnoreCase(ruta != null ? ruta : "")) {
            this.page = ruta;
        } else {
            this.page = "/WEB-INF/pages/welcome.xhtml";
        }        
    }

    public void muestraPage(String page, String ruta) {
        if (!"".equalsIgnoreCase(ruta != null ? ruta : "") && !"(null)".equalsIgnoreCase(ruta != null ? ruta : "")) {
            this.pageVentana = "/WEB-INF/pages/" + ruta + "/" + page + ".xhtml";
        } else {
            this.pageVentana = "/WEB-INF/pages/welcome.xhtml";
        }        
    }

    public void muestraPage(String ruta) {        
        if (!"".equalsIgnoreCase(ruta != null ? ruta : "") && !"(null)".equalsIgnoreCase(ruta != null ? ruta : "")) {
            this.pageVentana = ruta;
        } else {
            this.pageVentana = "/WEB-INF/pages/welcome.xhtml";
        }        
    }

    public String getCatalog() {
        return catalog;
    }

    /**
     *
     * @param catalog
     */
    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    /**
     *
     */
    public void checkCatalogs() {
        MktAdmin_CheckCatalogs.checkAllCatalogs();
    }

    /**
     *
     */
    public void saveUser() {
        FacesMessage message;
        MktAdmin_UsuarioDAO usuarioDAO = new MktAdmin_UsuarioDAO();

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("first_session_user", false);
        firstSession = false;
        usuario.setPassreset(false);
        if (usuarioDAO.saveUser(usuario)) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Information saved");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "There was a error while saving the information, " + usuarioDAO.getError());
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *
     */
    public void changeUserPassword() {
        usuario.setPassword(password);
        saveUser();
    }

    public void status() {
        System.out.println("registros proecesados: " + numRegistrosProcesados);
        System.out.println("registros totales: " + numRegistrosTotales);
    }
}
