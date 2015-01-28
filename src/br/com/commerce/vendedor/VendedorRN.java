package br.com.commerce.vendedor;

import java.util.Date;
import java.util.List;

import br.com.commerce.dao.VendedorDAO;

public class VendedorRN {
	
	private VendedorDAO vendedorDAO;
	
	public VendedorRN(){
		if(vendedorDAO == null){
			vendedorDAO = new VendedorDAO();
		}
	}
	
	public void salvar(Vendedor v){
		v.setDataCadastro(new Date());
		vendedorDAO.save(v);
	}
	
	public void alterar(Vendedor v){
		vendedorDAO.update(v);
	}
	
	public void deletar(Vendedor v){
		vendedorDAO.delete(v);
	}
	
	public List<Vendedor> listar(){
		List<Vendedor> lista = null;
		try {
			lista = vendedorDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
