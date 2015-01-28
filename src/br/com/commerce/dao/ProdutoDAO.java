package br.com.commerce.dao;

import java.util.List;

import org.hibernate.Query;

import br.com.commerce.produto.Produto;
import br.com.commerce.util.HibernateUtil;

public class ProdutoDAO extends GenericDAO<Produto>{
	
	@SuppressWarnings("unchecked")
	public List<Produto> listarPromocoes(){
		if(getSession().isOpen() == false){
			this.setSession(HibernateUtil.getSessionFactory().openSession());
		}
		List<Produto> listar = null;
		try {
			getSession().getTransaction().begin();
			String  hql = "select p from Produto p where p.promocao = :promocao";
			Query consulta = getSession().createQuery(hql);
			consulta.setLong("promocao", 1);
			listar = consulta.list();
			getSession().getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(getSession().isOpen() && getSession() != null){
				getSession().close();
			}
		}
		return listar;
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> listarProdutosPorCategoria(Long id){
		if(getSession().isOpen() == false){
			this.setSession(HibernateUtil.getSessionFactory().openSession());
		}
		List<Produto> listarPorCategoria = null;
		try {
			getSession().getTransaction().begin();
			Query consulta = getSession().createQuery("FROM Produto WHERE categoria_id = :codigo");
			consulta.setParameter("codigo", id);
			listarPorCategoria = consulta.list();
			getSession().getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(getSession().isOpen() != false){
				getSession().close();
			}
		}
		return listarPorCategoria;
	}
}
