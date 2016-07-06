package com.femsa.mkt.dao;

import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.DataBaseException;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.ObjectDeletedException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TypeMismatchException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.Transformers;

public class GenericDAO {

    private final HibernateUtil hibernateUtil;
    private final SessionFactory sessionFactory;
    private final Session session;
    private Transaction tx;

    public GenericDAO() throws DataBaseException {
        this.hibernateUtil = new HibernateUtil();
        sessionFactory = this.hibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public Session getSession() {
        return session;
    }

    public void closeDAO() {
        if (session != null && sessionFactory != null) {
            session.clear();
            session.close();
            sessionFactory.close();
        }
    }

    public <T, O extends Serializable> T findById(final Class clase, O id) throws DAOException {
        T elemento = null;
        try {
            elemento = (T) session.get(clase, id);
        } catch (TypeMismatchException e) {
            throw new DAOException("Error, parámetros incompatibles: " + e.getMessage());
        } finally {
            session.flush();
        }
        return elemento;
    }

    public <T extends Serializable> List<T> findAll(final Class clase) throws DAOException {
        List<T> elementos = null;
        Query query;
        try {
            query = session.createQuery("FROM " + clase.getName() + " c");
            elementos = query.list();
        } catch (HibernateException e) {
            throw new DAOException("Error no identificado: " + e.getMessage());
        } finally {
            session.flush();
        }
        return elementos;
    }

    public <T extends Serializable> void delete(final T persistentInstance) throws DAOException {
        try {
            tx = session.beginTransaction();
            session.delete(persistentInstance);
            tx.commit();
        } catch (HibernateException e) {
            throw new DAOException("Error: entidad no conocida o no válida, " + e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw new DAOException("Error: Argumentos no válidos, " + e2.getMessage());
        } finally {
            try {
                if (tx.isActive()) {
                    tx.rollback();
                }
                session.flush();
            } catch (ObjectDeletedException ex) {
                throw new DAOException("Error: al eliminar registro, " + ex.getMessage());
            } catch (ConstraintViolationException ex2) {
                throw new DAOException("Error: al eliminar registro, " + ex2.getMessage());
            }
        }
    }

    public <T extends Serializable> void delete(final List<T> instances) throws DAOException {
        try {
            tx = session.beginTransaction();
            if (instances != null && !instances.isEmpty()) {
                for (T instance : instances) {
                    session.delete(instance);
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            throw new DAOException("Error: entidad no conocida o no válida, " + e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw new DAOException("Error: entidad no conocida o no válida, " + e2.getMessage());
        } finally {
            try {
                if (tx.isActive()) {
                    tx.rollback();
                }
                session.flush();
            } catch (ObjectDeletedException ex) {
                throw new DAOException("Error: al eliminar registro, " + ex.getMessage());
            } catch (ConstraintViolationException ex2) {
                throw new DAOException("Error: al eliminar registro, " + ex2.getMessage());
            }
        }
    }

    public <T extends Serializable> void saveOrUpdate(final T instance) throws DAOException {
        saveOrUpdate(instance, true);
    }

    public <T extends Serializable> void saveOrUpdate(final T instance, boolean transaccion) throws DAOException {
        try {
            if (transaccion) {
                tx = session.beginTransaction();
            }
            session.saveOrUpdate(instance);
            if (transaccion) {
                tx.commit();
            }
        } catch (HibernateException e) {
            String message;
            message = e.getMessage(); 
            e.printStackTrace();
            throw new DAOException("Error al guardar la entidad: entidad no conocida o no válida, " + message);
        } catch (IllegalArgumentException e2) {
            String message;
            message = e2.getMessage();
            throw new DAOException("Error al guardar la entidad: entidad no conocida o no válida, " + message);
        } finally {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception ex) {
                String message;
                if (ex instanceof Throwable) {
                    message = ex.getCause().getMessage();
                } else {
                    message = ex.getMessage();
                }
                throw new DAOException("Error: No se puede guardar el registro, " + message);
            }
        }
    }

    public <T extends Serializable> void saveOrUpdateAll(final List<T> instances) throws DAOException {
        try {
            tx = session.beginTransaction();
            if (instances != null) {
                for (T instance : instances) {
                    session.saveOrUpdate(instance);
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new DAOException("Error: entidad no conocida o no válida, " + e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw new DAOException("Error: entidad no conocida o no válida, " + e2.getMessage());
        } finally {
            if (tx.wasCommitted()) {
                session.flush();
            }
            try {
                if (tx.isActive()) {
                    tx.rollback();
                    session.clear();
                }
            } catch (Exception ex) {
                throw new DAOException("Error: No se puede guardar el registro, " + ex.getMessage());
            }
        }
    }

    public <T extends Serializable> List< T> findByQuery(
            final Class clase,
            final String query) throws DAOException {
        List<T> elementos = null;
        Query queryHql;
        try {
            queryHql = session.createQuery(query);
            elementos = queryHql.list();
            if (elementos != null && !elementos.isEmpty()) {
                T muestra = elementos.get(0);
                if (muestra.getClass() == clase) {
                    return elementos;
                } else {
                    throw new DAOException("Error: Clases no compatibles");
                }
            }
        } catch (HibernateException e) {
            throw new DAOException("Error no identificado: " + e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw new DAOException("Error no identificado: " + e2.getMessage());
        } catch (Exception e2) {
            throw new DAOException("Error no identificado: " + e2.getMessage());
        } finally {
            session.flush();
        }
        return elementos;
    }

    public <T extends Serializable> List<T> findByComponent(
            final Class clase,
            final String columna,
            final Object valor) throws DAOException {
        List<T> elementos = null;
        Query queryHql;
        try {
            queryHql = session.createQuery("SELECT c FROM " + clase.getName() + " c WHERE c." + columna + " = :valor");
            queryHql.setParameter("valor", valor);
            elementos = queryHql.list();
            if (elementos != null && !elementos.isEmpty()) {
                T muestra = elementos.get(0);
                if (muestra.getClass() == clase) {
                    return elementos;
                } else {
                    throw new DAOException("Error: Clases no compatibles");
                }
            }
        } catch (HibernateException e) {
            throw new DAOException("Error no identificado: " + e.getMessage());
        } finally {
            session.flush();
        }
        return elementos;
    }

    public boolean excecuteNativeDDLSQL(final String sqlNative) throws DAOException {
        Query sql;
        try {
            if (sqlNative != null) {
                sql = session.createSQLQuery(sqlNative);
                sql.executeUpdate();
            } else {
                throw new DAOException("Error, can not excecute sentence: " + sqlNative);
            }
        } catch (HibernateException e) {
//            e.printStackTrace();
            sql = session.createSQLQuery("ROLLBACK");
            sql.executeUpdate();
            throw new DAOException("Error, can not excecute sentence: " + e.getMessage());
        } finally {
            session.flush();
        }
        return true;
    }
    
    public List<Object> excecuteNativeSQL(final String sqlNative) throws DAOException {
        Query sql;
        List<Object> res;
        try {
            if (sqlNative != null) {
                sql = session.createSQLQuery(sqlNative);
                res = sql.list();
            } else {
                throw new DAOException("Error, can not excecute sentence: " + sqlNative);
            }
        } catch (HibernateException e) {            
            throw new DAOException("Error, can not excecute sentence: " + e.getMessage());
        } finally {
            session.flush();
        }
        return res;
    }
    
    public List<Object> excecuteNativeSQL(final String sqlNative,final Class claseRes) throws DAOException {
        Query sql;
        List<Object> res;
        try {
            if (sqlNative != null) {
                sql = session.createSQLQuery(sqlNative).setResultTransformer(Transformers.aliasToBean(claseRes));
                res = sql.list();
            } else {
                throw new DAOException("Error, can not excecute sentence: " + sqlNative);
            }
        } catch (HibernateException e) {             
            throw new DAOException("Error, can not excecute sentence: " + e.getMessage());
        } finally {
            session.flush();
        }
        return res;
    }
}
