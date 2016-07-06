package com.femsa.mkt.dao;

import com.femsa.mkt.pojos.MktCobhDimEstado;
import com.femsa.mkt.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MktCobhDimEstadoDao {

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

    public List<MktCobhDimEstado> getEstadoAll() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT c FROM MktCobhDimEstado c");
        List<MktCobhDimEstado> estado = query.list();
        session.flush();
        session.clear();
        session.close();
        hibernateUtil.closeSessionFactory();
        return estado;
    }

    public MktCobhDimEstado getEstado(Integer id) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        MktCobhDimEstado estado = (MktCobhDimEstado) session.get(MktCobhDimEstado.class, id);
        session.flush();
        session.clear();
        session.close();
        hibernateUtil.closeSessionFactory();
        return estado;
    }

    public MktCobhDimEstado getEstado(String estado) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        MktCobhDimEstado EstadoT = null;
        try {
            Query query = session.createQuery("SELECT c FROM MktCobhDimEstado c WHERE c.estado = '" + estado.toUpperCase() + "'");
            List<MktCobhDimEstado> Estados = query.list();
            
            if (Estados.size() > 0) {
                EstadoT = Estados.get(0);
            }
        } catch (Exception e) {
            error = e.getMessage();
        } finally {
            session.flush();
            session.clear();
            session.close();
            hibernateUtil.closeSessionFactory();
        }

        return EstadoT;
    }

    public boolean saveEstado(MktCobhDimEstado Estado) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        try {
            session.beginTransaction();
            System.out.println(Estado);
            if ((Estado.getPkEstado() == null ? getEstado(Estado.getEstado()) : null) == null) {
                System.out.println("si entro");
                session.saveOrUpdate(Estado);
            } else {
                System.out.println("entro en error");
                error = "El Estado ya Existe";
                flagOk = false;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("entro en excepcion" + e.getMessage());
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

    public boolean EliminarEstado(MktCobhDimEstado Estado) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        try {
            session.beginTransaction();
            session.delete(Estado);
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
