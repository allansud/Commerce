package br.com.commerce.produto;

import java.util.List;

import br.com.commerce.dao.ProdutoDAO;

public class ProdutoRN {
	
	private ProdutoDAO produtoDAO;
	
	public ProdutoRN(){
		if(produtoDAO == null){
			produtoDAO = new ProdutoDAO();
		}
	}
	
	public void salvar(Produto p){
		produtoDAO.save(p);
	}
	
	public void alterar(Produto p){
		produtoDAO.update(p);
	}
	
	public void deletar(Produto p){
		produtoDAO.delete(p);
	}
	
	public List<Produto> listar(){
		List<Produto> lista = null;
		try {
			lista = produtoDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Produto> listaPromocoes(){
		return produtoDAO.listarPromocoes();
	}
	
	public Produto buscaPorID(Long id){
		return produtoDAO.findById(id);
	}
	
	public List<Produto> listarPorCategoria(Long id){
		return this.produtoDAO.listarProdutosPorCategoria(id);
	}
}
