package br.com.commerce.dao;

import java.util.List;

import org.hibernate.Query;

import br.com.commerce.endereco.Endereco;
import br.com.commerce.util.HibernateUtil;

public class EnderecoDAO extends GenericDAO<Endereco>{
	
	@SuppressWarnings("unchecked")
	public List<Endereco> enderecoPorUsuario(Long id){
		if(getSession().isOpen() == false){
			this.setSession(HibernateUtil.getSessionFactory().openSession());
		}
		List<Endereco> lista = null;
		try {
			getSession().getTransaction().begin();
			Query consulta = getSession().createQuery("FROM Endereco WHERE usuario_id = :codigo");
			consulta.setParameter("codigo", id);
			lista = consulta.list();
			getSession().getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(getSession().isOpen()){
				getSession().close();
			}
		}
		return lista;
	}
}
