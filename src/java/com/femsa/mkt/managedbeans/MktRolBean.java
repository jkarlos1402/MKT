package com.femsa.mkt.managedbeans;

import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.DataBaseException;
import com.femsa.mkt.pojos.MktAdmin_CatProyecto;
import com.femsa.mkt.pojos.MktAdmin_CatRol;
import com.femsa.mkt.pojos.MktCatMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author TMXIDSJPINAM
 */
@ManagedBean(name = "rolBean")
@ViewScoped
public class MktRolBean implements Serializable {

    private List<MktAdmin_CatRol> catRoles = new ArrayList<MktAdmin_CatRol>();
    private MktAdmin_CatRol rolSelected;
    private MktAdmin_CatRol rolNuevo = new MktAdmin_CatRol();

    private List<MktAdmin_CatProyecto> catProyectos = new ArrayList<MktAdmin_CatProyecto>();

    private List<MktCatMenu> catMenu = new ArrayList<MktCatMenu>();

    private TreeNode rootMenu;
    private TreeNode[] selectedNodesMenu;

    /**
     *
     */
    public MktRolBean() {
        startBean();
    }

    private void startBean() {
        GenericDAO genericDAO = null;
        try {
            genericDAO = new GenericDAO();
            catRoles = genericDAO.findAll(MktAdmin_CatRol.class);
            catProyectos = genericDAO.findAll(MktAdmin_CatProyecto.class);
            catMenu = genericDAO.findByQuery(MktCatMenu.class, "select m from MktCatMenu m where m.idpadre is null and m.idstatus = 1 order by m.idorden asc");
            rootMenu = new CheckboxTreeNode("Accesos: ", null);
            for (MktCatMenu itemMenu : catMenu) {
                armaSubMenu(itemMenu, rootMenu, null);
            }
        } catch (DataBaseException ex) {
            Logger.getLogger(MktRolBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(MktRolBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
    }

    private void armaSubMenu(MktCatMenu menuItem, TreeNode arbol, List<MktCatMenu> menuSelected) {
        if (!menuItem.getMenusHijo().isEmpty()) {
            TreeNode submenu = new CheckboxTreeNode(menuItem, arbol);
            for (MktCatMenu subMenuItem : menuItem.getMenusHijo()) {
                armaSubMenu(subMenuItem, submenu, menuSelected);
            }
        } else {
            TreeNode nodo = new CheckboxTreeNode(menuItem, arbol);
            if (menuSelected != null && menuSelected.contains((MktCatMenu) nodo.getData())) {
                nodo.setSelected(true);
            }
        }
    }

    public TreeNode getRootMenu() {
        return rootMenu;
    }

    public void setRootMenu(TreeNode rootMenu) {
        this.rootMenu = rootMenu;
    }

    public TreeNode[] getSelectedNodesMenu() {
        return selectedNodesMenu;
    }

    public void setSelectedNodesMenu(TreeNode[] selectedNodesMenu) {
        this.selectedNodesMenu = selectedNodesMenu;
    }

    public List<MktCatMenu> getCatMenu() {
        return catMenu;
    }

    public void setCatMenu(List<MktCatMenu> catMenu) {
        this.catMenu = catMenu;
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
    public MktAdmin_CatRol getRolSelected() {
        return rolSelected;
    }

    /**
     *
     * @param rolSelected
     */
    public void setRolSelected(MktAdmin_CatRol rolSelected) {
        this.rolSelected = rolSelected;
    }

    public MktAdmin_CatRol getRolNuevo() {
        return rolNuevo;
    }

    public void setRolNuevo(MktAdmin_CatRol rolNuevo) {
        this.rolNuevo = rolNuevo;
    }

    public List<MktAdmin_CatProyecto> getCatProyectos() {
        return catProyectos;
    }

    public void setCatProyectos(List<MktAdmin_CatProyecto> catProyectos) {
        this.catProyectos = catProyectos;
    }

    /**
     *
     */
    public void saveRol() {
        FacesMessage message;
        if (rolNuevo.getMenuList() != null) {
            rolNuevo.getMenuList().clear();
        }else{
            rolNuevo.setMenuList(new ArrayList<MktCatMenu>());
        }
        List<MktCatMenu> menusCompletos = new ArrayList<MktCatMenu>();
        for (int i = 0; i < selectedNodesMenu.length; i++) {
            TreeNode treeNode = selectedNodesMenu[i];
            MktCatMenu menuItem = (MktCatMenu) treeNode.getData();
            menusCompletos = obtieneListaMenuPadres(menusCompletos, menuItem);
            //rolNuevo.getMenuList().add(menuItem);
        }
        for (MktCatMenu menusCompleto : menusCompletos) {
            rolNuevo.getMenuList().add(menusCompleto);
        }
        GenericDAO genericDAO = null;
        try {
            genericDAO = new GenericDAO();
            genericDAO.saveOrUpdate(rolNuevo);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Rol guardado");
            refreshRoles();
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

    public void deleteRol() {
        FacesMessage message;
        GenericDAO genericDAO = null;
        try {
            genericDAO = new GenericDAO();
            genericDAO.delete(rolNuevo);
            refreshRoles();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Rol eliminado");
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

    private List<MktCatMenu> obtieneListaMenuPadres(List<MktCatMenu> listaMenu, MktCatMenu itemMenu) {
        if (listaMenu == null) {
            listaMenu = new ArrayList<MktCatMenu>();
        }
        if (itemMenu != null) {
            if (!listaMenu.contains(itemMenu)) {
                listaMenu.add(itemMenu);
            }
            if (itemMenu.getIdpadre() != null) {
                listaMenu = obtieneListaMenuPadres(listaMenu, itemMenu.getIdpadre());
            }
        }
        return listaMenu;
    }

    /**
     *
     */
    public void newRol() {
        rolNuevo = new MktAdmin_CatRol();
        rolSelected = null;
        rootMenu = new CheckboxTreeNode("Accesos: ", null);
        for (MktCatMenu itemMenu : catMenu) {
            armaSubMenu(itemMenu, rootMenu, null);
        }
    }

    /**
     *
     */
    public void selectRol() {
        rolNuevo.setPkRol(rolSelected.getPkRol());
        rolNuevo.setRol(rolSelected.getRol());
        rolNuevo.setIdProyecto(rolSelected.getIdProyecto());
        if (rolSelected.getMenuList() != null) {
            if (rolNuevo.getMenuList() == null) {
                rolNuevo.setMenuList(new ArrayList<MktCatMenu>());
            } else {
                rolNuevo.getMenuList().clear();
            }
            for (MktCatMenu menu : rolSelected.getMenuList()) {
                rolNuevo.getMenuList().add(menu);
            }
        }
        rootMenu = new CheckboxTreeNode("Accesos: ", null);
        for (MktCatMenu itemMenu : catMenu) {
            armaSubMenu(itemMenu, rootMenu, rolNuevo.getMenuList());
        }
    }

    /**
     *
     */
    public void refreshRoles() {
        GenericDAO genericDAO = null;
        try {
            genericDAO = new GenericDAO();
            catRoles = genericDAO.findAll(MktAdmin_CatRol.class);
        } catch (DataBaseException ex) {
            Logger.getLogger(MktRolBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(MktRolBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
    }
}
