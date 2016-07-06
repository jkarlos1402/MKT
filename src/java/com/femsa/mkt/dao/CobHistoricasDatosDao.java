package com.femsa.mkt.dao;
import com.femsa.mkt.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author TMXIDSCPEREZ
 */
public class CobHistoricasDatosDao {
 
    private List<String> errors = new ArrayList<String>();
    private HibernateUtil hibernateUtil = new HibernateUtil();
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

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

    
//    public boolean saveInfoPh(List<RvvdInfoPh> infoPh) {               
//        boolean flagOk = true;
//        long cont = 0L;
//        for (RvvdInfoPh carga : infoPh) {
//            session.save(carga);
//            if (cont % 100 == 0) {
//                session.flush();
//                session.clear();
//            }
//            cont++;
//        }
//        return flagOk;
//    }
}
