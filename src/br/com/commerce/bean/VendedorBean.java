package br.com.commerce.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.commerce.vendedor.Vendedor;
import br.com.commerce.vendedor.VendedorRN;

@ManagedBean
@ViewScoped
public class VendedorBean {
	
	private Vendedor vendedor = new Vendedor();
	private VendedorRN vendedorRN = new VendedorRN();
	private List<Vendedor> lista;
	
	public List<Vendedor> getLista(){
		
		if(lista == null){
			lista = vendedorRN.listar();
		}
		return lista;
	}
	
	public void alterar(){
		vendedorRN.alterar(vendedor);
	}
	
	public void deletar(Vendedor v){
		vendedorRN.deletar(v);
		this.lista = null;
	}
	
	public void salvar(){
		vendedorRN.salvar(vendedor);
		this.lista = null;
	}
	
	/**
	 * @return the vendedor
	 */
	public Vendedor getVendedor() {
		return vendedor;
	}

	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
}
