package com.femsa.mkt.managedbeans;

import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.DataBaseException;
import com.femsa.mkt.exception.MKTException;
import com.femsa.mkt.pojos.MktCobhScript;
import com.femsa.mkt.util.ScriptAnalizer;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author TMXIDSJPINAM
 */
@ManagedBean
@ViewScoped
public class MktScriptBean implements Serializable {

    private List<MktCobhScript> scripts;
    private MktCobhScript scriptSelected;
    private MktCobhScript scritpNuevo = new MktCobhScript();
    private String scriptText;
    List<String> logs = new ArrayList<String>();
    private static final String MSG_ERROR_TITULO = "Mensaje de error...";

    /**
     *
     */
    public MktScriptBean() {
        refreshScrpts();
    }

    /**
     *
     * @return
     */
    public String getScriptText() {
        return scriptText;
    }

    /**
     *
     * @param scriptText
     */
    public void setScriptText(String scriptText) {
        this.scriptText = scriptText;
    }

    public MktCobhScript getScritpNuevo() {
        return scritpNuevo;
    }

    public void setScritpNuevo(MktCobhScript scritpNuevo) {
        this.scritpNuevo = scritpNuevo;
    }

    /**
     *
     * @return
     */
    public List<MktCobhScript> getScripts() {
        return scripts;
    }

    /**
     *
     * @param scripts
     */
    public void setScripts(List<MktCobhScript> scripts) {
        this.scripts = scripts;
    }

    /**
     *
     * @return
     */
    public MktCobhScript getScriptSelected() {
        return scriptSelected;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    /**
     *
     * @param scriptSelected
     */
    public void setScriptSelected(MktCobhScript scriptSelected) {
        this.scriptSelected = scriptSelected;
        selectScript();
    }

    public void refreshScrpts() {
        GenericDAO genericDAO = null;
        try {
            genericDAO = new GenericDAO();
            scripts = genericDAO.findAll(MktCobhScript.class);
            scritpNuevo = new MktCobhScript();
        } catch (DataBaseException ex) {
            Logger.getLogger(MktScriptBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(MktScriptBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
    }

    public void verScript() {
        if (scriptSelected != null) {
            scritpNuevo = scriptSelected;
        }
    }

    public void nuevo() {
        scritpNuevo = new MktCobhScript();
        scriptSelected = null;
        logs.clear();
        scriptText = "";
    }

    public void selectScript() {
        FacesMessage message = null;
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = sc.getRealPath("");
        System.out.println(contextPathResources + scriptSelected.getRuta() + File.separator);
        File directorioBase = new File(contextPathResources + scriptSelected.getRuta() + File.separator);
        if (!directorioBase.isDirectory()) {
            directorioBase.mkdirs();
        }
        File[] ficheros = directorioBase.listFiles();
        File fichero;
        scriptText = "";
        for (int i = 0; i < ficheros.length; i++) {
            fichero = ficheros[i];
            if (scriptSelected.getNombreArchivo().equalsIgnoreCase(fichero.getName())) {
                try {
                    scriptText = MktCobhScript.getTextScript(directorioBase + File.separator + fichero.getName());
                    break;
                } catch (IOException ex) {
                    message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
                }
            }
        }
        if (message != null) {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     *
     */
    public void saveScriptSelected() {
        FacesMessage message = null;
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = sc.getRealPath("");
        File directorioBase = new File(contextPathResources + scriptSelected.getRuta());
        GenericDAO genericDAO = null;
        if (scriptSelected != null) {
            try {
                MktCobhScript.saveText(scriptText, directorioBase + File.separator + scriptSelected.getNombreArchivo());
                genericDAO = new GenericDAO();
                scriptSelected.setScript(scriptText);
                genericDAO.saveOrUpdate(scriptSelected);
                refreshScrpts();
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Script guardado");
            } catch (IOException ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            } catch (DataBaseException ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            } catch (DAOException ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void saveScriptNew() {
        FacesMessage message = null;
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = sc.getRealPath("");
        File directorioBase = null;
        if (scriptSelected == null) {
            directorioBase = new File(contextPathResources + File.separator + "WEB-INF" + File.separator + "script" + File.separator + scritpNuevo.getRuta());
            if (!directorioBase.isDirectory()) {
                directorioBase.mkdirs();
            }
            scritpNuevo.setRuta(File.separator + "WEB-INF" + File.separator + "script" + File.separator + scritpNuevo.getRuta());
            scriptText = "";
            scritpNuevo.setNombreArchivo(scritpNuevo.getDescripcion().replaceAll(" ", "") + ".sql");
        } else {
            directorioBase = new File(contextPathResources + File.separator + scritpNuevo.getRuta());
        }
        if (scritpNuevo != null) {
            GenericDAO genericDAO = null;
            try {
                genericDAO = new GenericDAO();
                MktCobhScript.saveText(scriptText, directorioBase + File.separator + scritpNuevo.getNombreArchivo());
                scritpNuevo.setScript(scriptText);
                genericDAO.saveOrUpdate(scritpNuevo);
                scriptSelected = scritpNuevo;
                scritpNuevo = new MktCobhScript();
                refreshScrpts();
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Script guardado");
            } catch (IOException ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            } catch (DataBaseException ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
                Logger.getLogger(MktScriptBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DAOException ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
                Logger.getLogger(MktScriptBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deleteScriptSelected() {
        FacesMessage message = null;
        GenericDAO genericDAO = null;
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = sc.getRealPath("");
        try {
            File directorioBase = new File(contextPathResources + scriptSelected.getRuta() + File.separator + scriptSelected.getNombreArchivo());
            genericDAO = new GenericDAO();
            directorioBase.delete();
            genericDAO.delete(scriptSelected);
            refreshScrpts();
            scriptSelected = null;
            scriptText = "";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Script eliminado");
        } catch (DataBaseException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            Logger.getLogger(MktScriptBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            Logger.getLogger(MktScriptBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void executeScriptSelected() {
        FacesMessage message = null;
        logs.clear();
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = sc.getRealPath("");
        File directorioBase = new File(contextPathResources + scriptSelected.getRuta() + File.separator);
        try {
            if (scriptSelected != null) {
                if (ScriptAnalizer.executeScritp(directorioBase + File.separator, scriptSelected.getNombreArchivo(), logs)) {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Script ejecutado");
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Script ejecutado con errores");
                }
            }
        } catch (IOException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            Logger.getLogger(MktScriptBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MKTException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage());
            Logger.getLogger(MktScriptBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
