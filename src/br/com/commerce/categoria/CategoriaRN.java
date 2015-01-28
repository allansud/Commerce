package br.com.commerce.categoria;

import java.util.List;

import br.com.commerce.dao.CategoriaDAO;

public class CategoriaRN {
	
	private CategoriaDAO categoriaDAO;
	
	public CategoriaRN(){
		if(categoriaDAO == null){
			categoriaDAO = new CategoriaDAO();
		}
	}
	
	public void salvar(Categoria c){
		categoriaDAO.save(c);
	}
	
	public void deletar(Categoria c){
		categoriaDAO.delete(c);
	}
	
	public void alterar(Categoria c){
		categoriaDAO.update(c);
	}
	
	public List<Categoria> listarCategorias(){
		List<Categoria> lista = null;
		try {
			lista = categoriaDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
