package br.com.commerce.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.commerce.categoria.Categoria;
import br.com.commerce.categoria.CategoriaRN;

@ManagedBean
@ViewScoped
public class CategoriaBean {
	
	private Categoria categoria = new Categoria();
	private List<Categoria> lista;
	CategoriaRN categoriaRN = new CategoriaRN();
	
	public void alterar(){
		categoriaRN.alterar(categoria);
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage("Nome categoria não pode estar vazio ou ter menos de 2 caracteres!");
		fc.addMessage(null, fm);
	}
	
	public void salvar(){
		if(categoria.getCategoria().length() >= 2 && categoria.getCategoria() != null){
			categoriaRN.salvar(categoria);
			this.lista = null;
		}else{
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Nome categoria não pode estar vazio ou ter menos de 2 caracteres!");
			fc.addMessage(null, fm);
		}
	}
	
	public List<Categoria> getLista() {
		if(lista == null){
			lista = categoriaRN.listarCategorias();
		}
		return lista;
	}

	public void setLista(List<Categoria> lista) {
		this.lista = lista;
	}

	public void deletar(Categoria categoria){
		categoriaRN.deletar(categoria);
		this.lista = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage("categoria deletada!!");
		fc.addMessage(null, fm);
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria c) {
		this.categoria = c;
	}
}
