package br.com.commerce.dao;


import org.hibernate.HibernateException;
import org.hibernate.Query;

import br.com.commerce.usuario.Usuario;
import br.com.commerce.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	public boolean verificarCadastro(String email){
    	if(getSession().isOpen() == false){
    		this.setSession(HibernateUtil.getSessionFactory().openSession());
    	}
		try {			
			getSession().getTransaction().begin();
	    	String hql = "select u from Usuario u where u.email = :email";
	    	Query consulta = getSession().createQuery(hql);
	    	consulta.setString("email", email);
	    	getSession().getTransaction().commit();
	    	
	    	if(consulta.list().size() > 0){
	    		return true;
	    	}else{
	    		return false;
	    	}
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			if(getSession().isOpen() != false){
				getSession().close();
			}
		}
		return false;
    } 
	
	public Usuario buscarPorLogin(String email){
		String hql = "select u from Usuario u where u.email = :email";
		Usuario usuario = null;
		
		try {
			getSession().getTransaction().begin();
			Query consulta = getSession().createQuery(hql);
			consulta.setString("email", email);
			usuario = (Usuario) consulta.uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			if(getSession() != null && getSession().isOpen()){
				getSession().close();
			}
		}
		return usuario;
	}
	
	public Usuario carregar(Long codigo){
		Usuario u = null;
		try {
			getSession().getTransaction().begin();
			u = (Usuario) getSession().get(Usuario.class, codigo);
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			if(getSession() != null && getSession().isOpen()){
				getSession().close();
			}
		}
		return u;
	}
}