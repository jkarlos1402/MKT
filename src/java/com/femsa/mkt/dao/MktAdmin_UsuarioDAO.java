package com.femsa.mkt.dao;
import com.femsa.mkt.pojos.MktAdmin_Usuario;
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
public class MktAdmin_UsuarioDAO {

    private String error;
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
     * @param user
     * @param password
     * @return
     */
    public MktAdmin_Usuario getUsuario(String user, String password) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        MktAdmin_Usuario usuario = null;
        try {
            Query query = session.createQuery("FROM MktAdmin_Usuario u WHERE u.usuario = '" + user.toUpperCase() + "' AND u.password = '" + password + "' AND u.estatus = 1");
            List<MktAdmin_Usuario> usuarios = query.list();

            if (!usuarios.isEmpty()) {
                return usuarios.get(0);
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
        return usuario;
    }

    /**
     *
     * @param user
     * @param filtrado
     * @return
     */
    public MktAdmin_Usuario getUsuario(String user, boolean filtrado) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        MktAdmin_Usuario usuario = null;
        Query query = null;
        try {
            if (filtrado) {
                query = session.createQuery("FROM MktAdmin_Usuario u WHERE u.usuario = '" + user.toUpperCase() + "' AND u.estatus = 1");
            } else {
                query = session.createQuery("FROM MktAdmin_Usuario u WHERE u.usuario = '" + user.toUpperCase() + "'");
            }
            System.out.println("FROM MktAdmin_Usuario u WHERE u.usuario = '" + user.toUpperCase() + "' AND u.estatus = 1");
            List<MktAdmin_Usuario> usuarios = query.list();
            if (!usuarios.isEmpty()) {
                return usuarios.get(0);
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
        return usuario;
    }

    /**
     *
     * @return
     */
    public List<MktAdmin_Usuario> getAllUsers() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<MktAdmin_Usuario> usuarios = null;
        try {
            Query query = session.createQuery("FROM MktAdmin_Usuario u");
            usuarios = (List<MktAdmin_Usuario>) query.list();
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
        return usuarios;
    }

    /**
     *
     * @param usuario
     * @return
     */
    public boolean saveUser(MktAdmin_Usuario usuario) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        boolean flagOk = true;
        try {
            session.beginTransaction();
            if ((usuario.getPkUsuario() == null ? getUsuario(usuario.getUsuario(), false) : null) == null) {
                session.saveOrUpdate(usuario);
                error = null;
            } else {
                error = "User already exists";
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
