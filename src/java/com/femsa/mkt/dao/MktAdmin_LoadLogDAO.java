package com.femsa.mkt.dao;

import com.femsa.mkt.pojos.MktAdmin_LoadLog;
import com.femsa.mkt.pojos.MktAdmin_Usuario;
import com.femsa.mkt.util.HibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Permite la administración de logs de carga
 *
 * @author TMXIDSJPINAM
 */
public class MktAdmin_LoadLogDAO {

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

    public boolean saveLog(MktAdmin_LoadLog log) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        try {
            session.beginTransaction();
            session.save(log);
            session.getTransaction().commit();
            error = null;
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

    public List<MktAdmin_LoadLog> getLogByUser(MktAdmin_Usuario usuario, String proyecto) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<MktAdmin_LoadLog> logs = null;
        Query query = null;
        try {
            if ("MARKETING".equalsIgnoreCase(proyecto)) {
                query = session.createQuery("SELECT sll FROM MktAdmin_LoadLog sll WHERE month(sll.fechaEjecucion) = month(CURRENT_DATE) AND NOMBRE_PROYECTO = 'MARKETING' AND sll.usuario.pkUsuario = " + usuario.getPkUsuario());
            }else if("share".equalsIgnoreCase(proyecto)){
                query = session.createQuery("SELECT sll FROM MktAdmin_LoadLog sll WHERE month(sll.fechaEjecucion) = month(CURRENT_DATE) AND NOMBRE_PROYECTO = 'SHARE' AND sll.usuario.pkUsuario = " + usuario.getPkUsuario());
            }
            logs = query != null ? query.list() : null;
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
        return logs;
    }

    public List<MktAdmin_LoadLog> getLogAll(String proyecto) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<MktAdmin_LoadLog> logs = null;
        Query query = null;
        try {
            if ("daily".equalsIgnoreCase(proyecto)) {
                query = session.createQuery("SELECT sll FROM MktAdmin_LoadLog sll WHERE month(sll.fechaEjecucion) = month(CURRENT_DATE) AND NOMBRE_PROYECTO = 'DAILY DASHBOARD'");
            }else if("share".equalsIgnoreCase(proyecto)){
                query = session.createQuery("SELECT sll FROM MktAdmin_LoadLog sll WHERE month(sll.fechaEjecucion) = month(CURRENT_DATE) AND NOMBRE_PROYECTO = 'SHARE'");
            }            
            logs = query != null ? query.list() : null;
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
        return logs;
    }
}
