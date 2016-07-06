package com.femsa.mkt.dao;

import com.femsa.mkt.pojos.MktAdmin_CatPais;
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
public class MktAdmin_CatPaisDAO {

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
     * @return
     */
    public List<MktAdmin_CatPais> getCatPais() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<MktAdmin_CatPais> countries = null;
        try {
            Query query = session.createQuery("SELECT pais FROM MktAdmin_CatPais pais WHERE pais.idstatus = 1");
            countries = query.list();
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
        return countries;
    }

    /**
     *
     * @param nombrePais
     * @return
     */
    public MktAdmin_CatPais getCatPais(String nombrePais) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<MktAdmin_CatPais> countries = null;
        MktAdmin_CatPais country = null;
        try {
            Query query = session.createQuery("SELECT pais FROM MktAdmin_CatPais pais WHERE pais.nombre = '" + nombrePais.toUpperCase() + "'");
            countries = query.list();
            if (!countries.isEmpty()) {
                country = countries.get(0);
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
        return country;
    }

    /**
     *
     * @return
     */
    public List<MktAdmin_CatPais> getCatPaisAll() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<MktAdmin_CatPais> countries = null;
        try {
            Query query = session.createQuery("SELECT pais FROM MktAdmin_CatPais");
            countries = query.list();
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
        return countries;
    }

    /**
     *
     * @param pais
     * @return
     */
    public boolean savePais(MktAdmin_CatPais pais) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = null;
        boolean flagOk = true;
        try {
            session.beginTransaction();
            if (pais.getPkPais() != null) {
                session.update(pais);
                error = null;
            } else if (getCatPais(pais.getNombre()) == null) {
                query = session.createSQLQuery("CREATE TABLE " + pais.getNombreTabla() + " (PAIS VARCHAR2(50 BYTE),CANAL VARCHAR2(50 BYTE), "
                        + "FECHA VARCHAR2(50 BYTE), GRUPO_CATEGORIA VARCHAR2(50 BYTE), CATEGORIA VARCHAR2(50 BYTE), "
                        + "FABRICANTE VARCHAR2(100 BYTE), VOLUMEN_MES NUMBER, VENTA_MES NUMBER)");
                query.executeUpdate();
                session.save(pais);
                error = null;
            } else {
                error = "Country already exists";
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
