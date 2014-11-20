package fr.jodev.elite.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.jodev.elite.exceptions.DataAccessLayerException;

/**
 * Generic abstract dao
 * @see <a href="http://www.java2s.com/Code/Java/Hibernate/GenericDaoFindAll.htm">GenericDao</a>
 */
public class AbstractDAO {
	private Session session;
    private Transaction tx;
    
    
    protected SessionFactory sessionFactory;

    public AbstractDAO() {
//    	HibernateFactory.buildIfNeeded();
    }

    protected void saveOrUpdate(Object obj) {
        try {
            startOperation();
            session.saveOrUpdate(obj);
//            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
//            HibernateFactory.close(session);
        }
    }

    protected void delete(Object obj) {
        try {
            startOperation();
            session.delete(obj);
//            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
//            HibernateFactory.close(session);
        }
    }

    protected Object find(Class<?> clazz, Long id) {
        Object obj = null;
        try {
            startOperation();
            obj = session.load(clazz, id);
//            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
//            HibernateFactory.close(session);
        }
        return obj;
    }

    protected List<?> findAll(Class<?> clazz) {
        List<?> objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName());
            objects = query.list();
//            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
//            HibernateFactory.close(session);
        }
        return objects;
    }

    protected void handleException(HibernateException e) throws DataAccessLayerException {
//        HibernateFactory.rollback(tx); // TODO: generic rollback in my case
    	tx.rollback(); // no tested yet ? TODO: test
        throw new DataAccessLayerException(e);
    }

    protected void startOperation() throws HibernateException {
//        session = HibernateFactory.openSession();
    	session = sessionFactory.getCurrentSession();
//        tx = session.beginTransaction();
    	tx = session.getTransaction();
    }
}
