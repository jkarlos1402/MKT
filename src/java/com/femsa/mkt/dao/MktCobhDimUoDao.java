package com.femsa.mkt.dao;

import com.femsa.mkt.pojos.MktCobhDimUo;
import com.femsa.mkt.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MktCobhDimUoDao {

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

    public List<MktCobhDimUo> getUoesAll() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT c FROM MktCobhDimUo c");
        List<MktCobhDimUo> uoes = query.list();
        session.flush();
        session.clear();
        session.close();
        hibernateUtil.closeSessionFactory();
        return uoes;
    }

    public MktCobhDimUo getUo(Integer id) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        MktCobhDimUo uo = (MktCobhDimUo) session.get(MktCobhDimUo.class, id);
        session.flush();
        session.clear();
        session.close();
        hibernateUtil.closeSessionFactory();
        return uo;
    }

    public MktCobhDimUo getUo(String uo) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT c FROM MktCobhDimUo c WHERE c.uo = '" + uo.toUpperCase() + "'");
        List<MktCobhDimUo> uoes = query.list();
        MktCobhDimUo uoT = null;
        if (uoes.size() > 0) {
            uoT = uoes.get(0);
        }
        session.flush();
        session.clear();
        session.close();
        hibernateUtil.closeSessionFactory();
        return uoT;
    }

    public boolean saveUo(MktCobhDimUo uo) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        try {
            session.beginTransaction();
            if ((uo.getPkUo() == null ? getUo(uo.getUo()) : null) == null) {
                
                session.saveOrUpdate(uo);
            } else {
                error = "El Uo ya existe";
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

    public boolean EliminarUo(MktCobhDimUo uo) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        try {
            session.beginTransaction();
            session.delete(uo);
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
