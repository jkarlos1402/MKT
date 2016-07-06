package com.femsa.mkt.util;

import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.DataBaseException;
import com.femsa.mkt.exception.MKTException;
import com.femsa.mkt.pojos.MktCobhDimAgrupador;
import com.femsa.mkt.pojos.MktCobhDimCanal;
import com.femsa.mkt.pojos.MktCobhDimCanalkof;
import com.femsa.mkt.pojos.MktCobhDimCanalrm1;
import com.femsa.mkt.pojos.MktCobhDimCategoria;
import com.femsa.mkt.pojos.MktCobhDimCategoriaestra;
import com.femsa.mkt.pojos.MktCobhDimCliente;
import com.femsa.mkt.pojos.MktCobhDimEmpaque;
import com.femsa.mkt.pojos.MktCobhDimEstado;
import com.femsa.mkt.pojos.MktCobhDimFamiliamarca;
import com.femsa.mkt.pojos.MktCobhDimGec;
import com.femsa.mkt.pojos.MktCobhDimGrupoRm1;
import com.femsa.mkt.pojos.MktCobhDimGrupoRm2;
import com.femsa.mkt.pojos.MktCobhDimGrupocanal;
import com.femsa.mkt.pojos.MktCobhDimGrupocategoria;
import com.femsa.mkt.pojos.MktCobhDimMarca;
import com.femsa.mkt.pojos.MktCobhDimNivelmercadeo;
import com.femsa.mkt.pojos.MktCobhDimOcaconsumo;
import com.femsa.mkt.pojos.MktCobhDimPlanvisita;
import com.femsa.mkt.pojos.MktCobhDimRegion;
import com.femsa.mkt.pojos.MktCobhDimRetornable;
import com.femsa.mkt.pojos.MktCobhDimRuta;
import com.femsa.mkt.pojos.MktCobhDimSegcalorico;
import com.femsa.mkt.pojos.MktCobhDimSku;
import com.femsa.mkt.pojos.MktCobhDimSubcategoria;
import com.femsa.mkt.pojos.MktCobhDimTamanio;
import com.femsa.mkt.pojos.MktCobhDimTerritorio;
import com.femsa.mkt.pojos.MktCobhDimTipoconsumo;
import com.femsa.mkt.pojos.MktCobhDimUo;
import com.femsa.mkt.pojos.MktCobhLogStatus;
import com.femsa.mkt.pojos.MktCobhRelarchivotabla;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.ServletContext;

/**
 *
 * @author TMXIDSJPINAM
 */
public class MktAdmin_CatalogLoader {

    /**
     *
     */
    public static String error = "";

    /**
     *
     * @param proyecto
     * @throws com.femsa.mkt.exception.MKTException
     *
     */
    public static void loadCatalogs(String proyecto) throws MKTException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        GenericDAO genericDAO = null;
        try {
            genericDAO = new GenericDAO();
            if ("MARKETING".equalsIgnoreCase(proyecto)) {
                //agregar carga inicial de catalogos faltantes aqui
                sc.setAttribute("dimEstado", genericDAO.findAll(MktCobhDimEstado.class));
                sc.setAttribute("dimRegion", genericDAO.findAll(MktCobhDimRegion.class));
                sc.setAttribute("dimCanal", genericDAO.findAll(MktCobhDimCanal.class));
                sc.setAttribute("dimUO", genericDAO.findAll(MktCobhDimUo.class));
                sc.setAttribute("dimMarca", genericDAO.findAll(MktCobhDimMarca.class));
                sc.setAttribute("dimTamanio", genericDAO.findAll(MktCobhDimTamanio.class));
                sc.setAttribute("dimEmpaque", genericDAO.findAll(MktCobhDimEmpaque.class));
                sc.setAttribute("dimRetornable", genericDAO.findAll(MktCobhDimRetornable.class));
                sc.setAttribute("dimCategoria", genericDAO.findAll(MktCobhDimCategoria.class));
                sc.setAttribute("dimGrupoRM1", genericDAO.findAll(MktCobhDimGrupoRm1.class));
                sc.setAttribute("dimGrupoRM2", genericDAO.findAll(MktCobhDimGrupoRm2.class));
                sc.setAttribute("dimGec", genericDAO.findAll(MktCobhDimGec.class));
                sc.setAttribute("dimTipoConsumo", genericDAO.findAll(MktCobhDimTipoconsumo.class));
                sc.setAttribute("dimCanalRM1", genericDAO.findAll(MktCobhDimCanalrm1.class));
                sc.setAttribute("dimCanalKOF", genericDAO.findAll(MktCobhDimCanalkof.class));
                sc.setAttribute("dimPlanVisita", genericDAO.findAll(MktCobhDimPlanvisita.class));
                sc.setAttribute("dimTerritorio", genericDAO.findAll(MktCobhDimTerritorio.class));
                sc.setAttribute("dimRuta", genericDAO.findAll(MktCobhDimRuta.class));
                sc.setAttribute("dimSKU", genericDAO.findAll(MktCobhDimSku.class));
                sc.setAttribute("dimCliente", genericDAO.findAll(MktCobhDimCliente.class));
                sc.setAttribute("dimOcaConsumo", genericDAO.findAll(MktCobhDimOcaconsumo.class));
                sc.setAttribute("dimFamiliaMarca", genericDAO.findAll(MktCobhDimFamiliamarca.class));
                sc.setAttribute("dimNivelMercadeo", genericDAO.findAll(MktCobhDimNivelmercadeo.class));
                sc.setAttribute("dimSubcategoria", genericDAO.findAll(MktCobhDimSubcategoria.class));
                sc.setAttribute("dimCategoriaestra", genericDAO.findAll(MktCobhDimCategoriaestra.class));
                sc.setAttribute("dimGrupoCanal", genericDAO.findAll(MktCobhDimGrupocanal.class));
                sc.setAttribute("dimGrupoCategoria", genericDAO.findAll(MktCobhDimGrupocategoria.class));
                sc.setAttribute("dimSegmentoCalorico", genericDAO.findAll(MktCobhDimSegcalorico.class));
                sc.setAttribute("dimAgrupador", genericDAO.findAll(MktCobhDimAgrupador.class));
                sc.setAttribute("catCobhLogStatus", genericDAO.findAll(MktCobhLogStatus.class));
            }
        } catch (DataBaseException ex) {
            throw new MKTException(ex.getMessage());
        } catch (DAOException ex) {
            throw new MKTException(ex.getMessage());
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
    }

    public static void loadCatalogsDinamicos(String paquetePojos, String proyecto) {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = sc.getRealPath("");
        String[] dirs = paquetePojos.split("\\.");
        String rutaPaquete = "";
        for (String dir : dirs) {
            rutaPaquete += dir + File.separator;
        }
        File directorioBase = new File(contextPathResources + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + rutaPaquete);
        File[] clases = directorioBase.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".class");
            }
        });
        GenericDAO genericDAO = null;
        if ("MARKETING".equalsIgnoreCase(proyecto)) {
            List<MktCobhRelarchivotabla> camposCargaCobh;
            List<MktCobhRelarchivotabla> camposCargaLanzObjetivos;
            List<MktCobhRelarchivotabla> camposCargaLanzIdCliente;
            List<MktCobhRelarchivotabla> camposCargaPromObjetivos;
            List<MktCobhRelarchivotabla> camposCargaPromID;
            List<MktCobhRelarchivotabla> camposCargaPromCanal;
            try {
                genericDAO = new GenericDAO();
                camposCargaCobh = genericDAO.findByQuery(MktCobhRelarchivotabla.class, "select rat from MktCobhRelarchivotabla rat where rat.fkIdproyecto = 1 and rat.fkIdprocesoenc = 1 order by rat.ordenEval asc");
                sc.setAttribute("camposCobH", camposCargaCobh);
                camposCargaLanzObjetivos = genericDAO.findByQuery(MktCobhRelarchivotabla.class, "select rat from MktCobhRelarchivotabla rat where rat.fkIdproyecto = 1 and rat.fkIdprocesoenc = 2 order by rat.ordenEval asc");
                sc.setAttribute("camposLanzObjetivos", camposCargaLanzObjetivos);
                camposCargaLanzIdCliente = genericDAO.findByQuery(MktCobhRelarchivotabla.class, "select rat from MktCobhRelarchivotabla rat where rat.fkIdproyecto = 1 and rat.fkIdprocesoenc = 3 order by rat.ordenEval asc");
                sc.setAttribute("camposLanzIdCliente", camposCargaLanzIdCliente);
                camposCargaPromObjetivos = genericDAO.findByQuery(MktCobhRelarchivotabla.class, "select rat from MktCobhRelarchivotabla rat where rat.fkIdproyecto = 1 and rat.fkIdprocesoenc = 4 order by rat.ordenEval asc");
                sc.setAttribute("camposPromObjetivos", camposCargaPromObjetivos);
                camposCargaPromID = genericDAO.findByQuery(MktCobhRelarchivotabla.class, "select rat from MktCobhRelarchivotabla rat where rat.fkIdproyecto = 1 and rat.fkIdprocesoenc = 5 order by rat.ordenEval asc");
                sc.setAttribute("camposPromId", camposCargaPromID);
                camposCargaPromCanal = genericDAO.findByQuery(MktCobhRelarchivotabla.class, "select rat from MktCobhRelarchivotabla rat where rat.fkIdproyecto = 1 and rat.fkIdprocesoenc = 6 order by rat.ordenEval asc");
                sc.setAttribute("camposPromCanal", camposCargaPromCanal);
                Class clasePojo;
                if (camposCargaCobh != null) {
                    for (MktCobhRelarchivotabla mktCobhRelarchivotabla : camposCargaCobh) {
                        if (mktCobhRelarchivotabla.getNombreTablaCatalogo() != null) {
                            for (int i = 0; i < clases.length; i++) {
                                try {
                                    clasePojo = Class.forName(paquetePojos + "." + clases[i].getName().replace(".class", ""));
                                    if (clasePojo.isAnnotationPresent(Table.class) && ((Table) clasePojo.getAnnotation(Table.class)).name().equals(mktCobhRelarchivotabla.getNombreTablaCatalogo().trim())) {
                                        List<Serializable> listCatalogo = genericDAO.findAll(clasePojo);
                                        if (listCatalogo == null) {
                                            listCatalogo = new ArrayList<Serializable>();
                                        }
                                        if (mktCobhRelarchivotabla.getNombreTablaCatalogo() != null && !mktCobhRelarchivotabla.getRequerido()) {
                                            Object catalogoSN = generaFaltanteCampos(clasePojo);
                                            if (!listCatalogo.contains(catalogoSN)) {
                                                listCatalogo.add((Serializable)catalogoSN);
                                            }
                                        }
                                        sc.setAttribute("CAT_" + mktCobhRelarchivotabla.getNombreTablaCatalogo(), listCatalogo);
                                        sc.setAttribute("CLASS_CAT_" + mktCobhRelarchivotabla.getNombreTablaCatalogo(), clasePojo);
                                        break;
                                    }
                                } catch (ClassNotFoundException ex) {
//                                    ex.printStackTrace();
                                } catch (MKTException ex) {
//                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
            } catch (DataBaseException ex) {
                Logger.getLogger(MktAdmin_CatalogLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DAOException ex) {
                ex.printStackTrace();
                Logger.getLogger(MktAdmin_CatalogLoader.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (genericDAO != null) {
                    genericDAO.closeDAO();
                }
            }
        }
    }

    public static void saveCatalogosDinamicos() throws MKTException {
        GenericDAO genericDAO = null;
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        List<MktCobhRelarchivotabla> camposCobh = (List<MktCobhRelarchivotabla>) sc.getAttribute("camposCobH");
        try {
            genericDAO = new GenericDAO();
            if (camposCobh != null) {
                for (MktCobhRelarchivotabla mktCobhRelarchivotabla : camposCobh) {
                    if (mktCobhRelarchivotabla.getNombreTablaCatalogo() != null) {
                        System.out.println(((List<Serializable>) sc.getAttribute("CAT_" + mktCobhRelarchivotabla.getNombreTablaCatalogo())).size() + " - " + mktCobhRelarchivotabla.getNombreTablaCatalogo());
                        genericDAO.saveOrUpdateAll((List<Serializable>) sc.getAttribute("CAT_" + mktCobhRelarchivotabla.getNombreTablaCatalogo()));
                    }
                }
            }
        } catch (DAOException ex) {
            loadCatalogsDinamicos("com.femsa.mkt.pojos", "MARKETING");
            throw new MKTException(ex.getMessage());
        } catch (DataBaseException ex) {
            loadCatalogsDinamicos("com.femsa.mkt.pojos", "MARKETING");
            throw new MKTException(ex.getMessage());
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
    }

    private static Object generaFaltanteCampos(Class claseObjeto) throws MKTException {
        if (claseObjeto != null) {
            try {
                Field[] camposObjeto = claseObjeto.getDeclaredFields();
                Object o = claseObjeto.newInstance();
                for (Field field : camposObjeto) {
                    if (!field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(Column.class) && field.getType().getName().equals(String.class.getName())) {
                        field.setAccessible(true);
                        field.set(o, "S/N");
                    }
                }
                return o;
            } catch (InstantiationException ex) {
                throw new MKTException(ex.getMessage());
            } catch (IllegalAccessException ex) {
                throw new MKTException(ex.getMessage());
            }
        }
        return null;
    }
}
