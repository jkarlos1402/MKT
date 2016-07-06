/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.managedbeans;

import com.femsa.mkt.dao.MktAdmin_UsuarioDAO;
import com.femsa.mkt.exception.MKTException;
import com.femsa.mkt.pojos.MktAdmin_Usuario;
import com.femsa.mkt.util.MktAdmin_CatalogLoader;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TMXIDSJPINAM
 */
@ManagedBean
@RequestScoped
public class MktLoginBean {

    private String user;
    private String password;

    private String error;
    private static final String MSG_ERROR_TITULO = "Mensaje de error...";

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
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
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
    public String logIn() {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        if (error != null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + error, ""));
            try {
                MktAdmin_CatalogLoader.loadCatalogs("MARKETING");
            } catch (MKTException ex) {
                servletContext.setAttribute("error_database", ex.getMessage());
                error = null;
            }
            return "Login";
        }
        try {
            MktAdmin_UsuarioDAO usuarioDAO = new MktAdmin_UsuarioDAO();
            MktAdmin_Usuario usuario = usuarioDAO.getUsuario(user, true);
            if (usuario != null && usuario.getPassword().equalsIgnoreCase(password)) {
                HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                if (usuario.getLastLogin() == null) {
                    httpSession.setAttribute("first_session_user", true);
                } else {
                    httpSession.setAttribute("first_session_user", false);
                }
                usuario.setIntentos(0);
                usuario.setLastLogin(new Date());
                usuarioDAO.saveUser(usuario);
                httpSession.setAttribute("session_user", usuario);
                System.out.println("Ya entro Autorizado");
                return "correct";
            } else if (usuario != null) {
                usuario.setIntentos(usuario.getIntentos() + 1);
                if (usuario.getIntentos() == 3) {
                    usuario.setEstatus(false);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention: The user has been blocked, number of attempts exceeded, Contact the administrator", ""));
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: User or password incorrect", ""));
                    System.out.println("NO ENTRO" + password);
                }
                usuarioDAO.saveUser(usuario);
                System.out.println("Ya entro Error de Datos");
                return "Login";
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: User or password incorrect", ""));
                return "Login";
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, MSG_ERROR_TITULO, e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), ""));
            return "Login";
        }
    }

    /**
     *
     * @return
     */
    public String logout() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (httpSession != null) {
            httpSession.invalidate();
        }
        return "Login";
    }

}
