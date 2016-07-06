package com.femsa.mkt.dao;

import com.femsa.mkt.pojos.MktCobStHisObjetivos;
import com.femsa.mkt.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TMXIDSCPEREZ
 */
public class CobHistoricoDao_Objetivos {

    public boolean guardarLista(List<MktCobStHisObjetivos> listaAgrupacion) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        if (listaAgrupacion != null) {
            try {
                session.beginTransaction();
                for (MktCobStHisObjetivos mktCobStHisObjetivos : listaAgrupacion) {
                    session.save(mktCobStHisObjetivos);
                }
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
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
        }
        return flagOk;
    }

    public boolean boorrarTabla() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        Query query = session.createSQLQuery("TRUNCATE TABLE MKT_COB_ST_HIS_AGRUPACION");
        try {
            session.beginTransaction();
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
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
        
    public boolean EjecutaEtl() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        Query query = session.createSQLQuery("TRUNCATE TABLE MKT_COB_ST_HIS_AGRUPACION");
        try {
            session.beginTransaction();
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
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
