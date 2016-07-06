package com.femsa.mkt.dao;

import com.femsa.mkt.pojos.MktAdmin_CatProyecto;
import com.femsa.mkt.util.HibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author TMXIDSJPINAM
 */
public class MktAdmin_CatProyectoDAO {

    private String error = "";
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
     * @param id
     * @return
     */
    public MktAdmin_CatProyecto getProyecto(Integer id) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        MktAdmin_CatProyecto proyecto = null;
        try {
            proyecto = (MktAdmin_CatProyecto) session.get(MktAdmin_CatProyecto.class, id);
            error = null;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, MSG_ERROR_TITULO, e);
            error = e.getMessage();
        } finally {
            session.flush();
            session.clear();
            session.close();
            hibernateUtil.closeSessionFactory();
        }
        return proyecto;
    }

    /**
     *
     * @return
     */
    public List<MktAdmin_CatProyecto> getCatProyectos() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<MktAdmin_CatProyecto> proyectos = null;
        try {
            Query query = session.createQuery("SELECT proyecto FROM MktAdmin_CatProyecto proyecto WHERE proyecto.status = 1");
            proyectos = query.list();
            error = null;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, MSG_ERROR_TITULO, e);
            error = e.getMessage();
        } finally {
            session.flush();
            session.clear();
            session.close();
            hibernateUtil.closeSessionFactory();
        }
        return proyectos;
    }

    /**
     *
     * @param nombrProyecto
     * @return
     */
    public MktAdmin_CatProyecto getProyecto(String nombrProyecto) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        MktAdmin_CatProyecto proyecto = null;
        try {
            Query query = session.createQuery("SELECT proyecto FROM MktAdmin_CatProyecto proyecto WHERE proyecto.nombreProyecto = '" + nombrProyecto.toUpperCase() + "'");
            List<MktAdmin_CatProyecto> proyectos = query.list();
            if (!proyectos.isEmpty()) {
                proyecto = proyectos.get(0);
            }
            error = null;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, MSG_ERROR_TITULO, e);
            error = e.getMessage();
        } finally {
            session.flush();
            session.clear();
            session.close();
            hibernateUtil.closeSessionFactory();
        }
        return proyecto;
    }

    /**
     *
     * @return
     */
    public List<MktAdmin_CatProyecto> getCatProyectosAll() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<MktAdmin_CatProyecto> proyectos = null;
        try {
            Query query = session.createQuery("SELECT proyecto FROM MktAdmin_CatProyecto proyecto");
            proyectos = query.list();
            error = null;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, MSG_ERROR_TITULO, e);
            error = e.getMessage();
        } finally {
            session.flush();
            session.clear();
            session.close();
            hibernateUtil.closeSessionFactory();
        }
        return proyectos;
    }

    /**
     *
     * @param proyecto
     * @return
     */
    public boolean saveProyecto(MktAdmin_CatProyecto proyecto) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = null;
        boolean flagOk = true;
        try {
            session.beginTransaction();
            if (proyecto.getIdProyecto() != null) {
                session.update(proyecto);
                error = null;
            } else if (getProyecto(proyecto.getNombreProyecto()) == null) {
                session.save(proyecto);
                error = null;
            } else {
                error = "Project already exists";
                flagOk = false;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, MSG_ERROR_TITULO, e);
            error = e.getMessage();
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            flagOk = false;
        } finally {
            session.flush();
            session.clear();
            session.close();
            hibernateUtil.closeSessionFactory();
        }
        return flagOk;
    }
}
