package br.com.commerce.fabricante;

import java.util.List;

import br.com.commerce.dao.FabricanteDAO;

public class FabricanteRN {
	
	private FabricanteDAO fabricanteDAO;
	
	public FabricanteRN(){
		if(fabricanteDAO == null){
			fabricanteDAO = new FabricanteDAO();
		}
	}
	
	public void salvar(Fabricante f){
		fabricanteDAO.save(f);
	}
	
	public void alterar(Fabricante f){
		fabricanteDAO.update(f);
	}
	
	public void deletar(Fabricante f){
		fabricanteDAO.delete(f);
	}
	
	public List<Fabricante> listarFabricante(){
		List<Fabricante> lista = null;
		try {
			lista = fabricanteDAO.findAll();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return lista;
	}

}
