package com.femsa.mkt.dao;

import com.femsa.mkt.pojos.MktCobhDimCanal;
import com.femsa.mkt.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MktCobhDimCanalDao {

    private List<String> errors = new ArrayList<String>();
    private HibernateUtil hibernateUtil = new HibernateUtil();
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<MktCobhDimCanal> getCanalesAll() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT c FROM MktCobhDimCanal c");
        List<MktCobhDimCanal> canales = query.list();
        session.flush();
        session.clear();
        session.close();
        hibernateUtil.closeSessionFactory();
        return canales;
    }

    public MktCobhDimCanal getCanal(Integer id) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        MktCobhDimCanal canal = (MktCobhDimCanal) session.get(MktCobhDimCanal.class, id);
        session.flush();
        session.clear();
        session.close();
        hibernateUtil.closeSessionFactory();
        return canal;
    }

    public MktCobhDimCanal getCanal(String canal) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT c FROM MktCobhDimCanal c WHERE c.canalTexto = '" + canal.toUpperCase() + "'");
        List<MktCobhDimCanal> canales = query.list();
        MktCobhDimCanal canalT = null;
        if (canales.size() > 0) {
            canalT = canales.get(0);
        }
        session.flush();
        session.clear();
        session.close();
        hibernateUtil.closeSessionFactory();
        return canalT;
    }

    public boolean saveCanal(MktCobhDimCanal canal) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        try {
            session.beginTransaction();
            if ((canal.getPkCanal() == null ? getCanal(canal.getCanaltexto()) : null) == null) {
                
                session.saveOrUpdate(canal);
            } else {
                error = "El Canal ya existe";
                flagOk = false;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
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

    public boolean EliminarCanal(MktCobhDimCanal canal) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        try {
            session.beginTransaction();
            session.delete(canal);
            session.getTransaction().commit();
        } catch (Exception e) {
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

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public HibernateUtil getHibernateUtil() {
        return hibernateUtil;
    }

    public void setHibernateUtil(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
