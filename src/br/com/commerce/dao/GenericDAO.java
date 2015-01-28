package br.com.commerce.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.commerce.util.HibernateUtil;

public class GenericDAO<T extends Serializable> {
	 
    private Session session;
	private final Class<T> persistentClass;
 
    @SuppressWarnings("unchecked")
	public GenericDAO() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        this.persistentClass = (Class<T>) ((ParameterizedType) 
            getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
 
    public Session getSession() {
        return session;
    }
    
    public void setSession(Session session) {
		this.session = session;
	}
 
    public void save(T entity) {
    	if(getSession().isOpen() == false){
    		this.session = HibernateUtil.getSessionFactory().openSession();
    	}
        try {
            getSession().getTransaction().begin();
            getSession().save(entity);          
            getSession().getTransaction().commit();
            
        } catch (Throwable t) {
            getSession().getTransaction().rollback();
            t.printStackTrace();
        } finally {
            close();
        }
    }
 
    public void update(T entity) {
    	if(getSession().isOpen() == false){
    		this.session = HibernateUtil.getSessionFactory().openSession();
    	}
        try {
            getSession().getTransaction().begin();
            getSession().update(entity);
            getSession().getTransaction().commit();
        } catch (Throwable t) {
            getSession().getTransaction().rollback();
            t.printStackTrace();
        } finally {
            close();
        }
    }
 
    public void delete(T entity) {
    	if(getSession().isOpen() == false){
    		this.session = HibernateUtil.getSessionFactory().openSession();
    	}
        try {
        	
            getSession().getTransaction().begin();
            getSession().delete(entity);
            getSession().getTransaction().commit();
            
        } catch (Throwable t) {
            getSession().getTransaction().rollback();
            t.printStackTrace();
        } finally {
            close();
        }
    }
 
    @SuppressWarnings("unchecked")
	public List<T> findAll() throws Exception {
    	if(getSession().isOpen() == false){
    		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    	}
    	
    	List<T> lista = null;
    	try {
			getSession().getTransaction().begin();
			lista = getSession().createCriteria(persistentClass).list();
			getSession().getTransaction().commit();
			
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		} finally{
			close();
		}
    	return lista;
    }
 
    @SuppressWarnings("unchecked")
	public T findByName(String nome) {
    	if(getSession().isOpen() == false){
    		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    	}
    	T t = null;
    	try {
			getSession().getTransaction().begin();
			t = (T) getSession().createCriteria(persistentClass)
					.add(Restrictions.eq("nome", nome).ignoreCase()).uniqueResult();
			getSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close();
		}
    	return t;
    }
 
    @SuppressWarnings("unchecked")
	public T findById(long id) {
    	if(getSession().isOpen() == false){
    		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    	}
    	T t = null;
    	try {
			getSession().getTransaction().begin();
			t = (T) getSession().createCriteria(persistentClass)
					.add(Restrictions.eq("id", id)).uniqueResult();
			getSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close();
		}
        return t;
    }
 
    private void close() {
        if (getSession() != null && getSession().isOpen()) {
            getSession().close();
        }
    }
}

