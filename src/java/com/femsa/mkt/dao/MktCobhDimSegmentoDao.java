package com.femsa.mkt.dao;

import com.femsa.mkt.pojos.MktCobhDimSegmento;
import com.femsa.mkt.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MktCobhDimSegmentoDao {

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

    public List<MktCobhDimSegmento> getSegmentoesAll() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT c FROM MktCobhDimSegmento c");
        List<MktCobhDimSegmento> segmentoes = query.list();
        session.flush();
        session.clear();
        session.close();
        hibernateUtil.closeSessionFactory();
        return segmentoes;
    }

    public MktCobhDimSegmento getSegmento(Integer id) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        MktCobhDimSegmento segmento = null;
        if (id != null) {
            segmento = (MktCobhDimSegmento) session.get(MktCobhDimSegmento.class, id);
        }
        session.flush();
        session.clear();
        session.close();
        hibernateUtil.closeSessionFactory();
        return segmento;
    }

    public MktCobhDimSegmento getSegmento(String segmento) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT c FROM MktCobhDimSegmento c WHERE c.segmento = '" + segmento.toUpperCase() + "'");
        List<MktCobhDimSegmento> segmentoes = query.list();
        MktCobhDimSegmento segmentoT = null;
        if (segmentoes.size() > 0) {
            segmentoT = segmentoes.get(0);
        }
        session.flush();
        session.clear();
        session.close();
        hibernateUtil.closeSessionFactory();
        return segmentoT;
    }

    public boolean saveSegmento(MktCobhDimSegmento segmento) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        try {
            session.beginTransaction();
            if ((segmento.getPkSegmento() == null ? getSegmento(segmento.getSegmentoTexto()) : null) == null) {

                session.saveOrUpdate(segmento);
            } else {
                error = "El Segmento ya existe";
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

    public boolean EliminarSegmento(MktCobhDimSegmento segmento) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        try {
            session.beginTransaction();
            session.delete(segmento);
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
