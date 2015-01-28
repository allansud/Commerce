package br.com.commerce.bean;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.commerce.fabricante.Fabricante;
import br.com.commerce.fabricante.FabricanteRN;

@ManagedBean(name = "fabricanteBean")
@ViewScoped
public class FabricanteBean {
	
	private Fabricante fabricante = new Fabricante();
	private List<Fabricante> listaFabricante;
	FabricanteRN fabricanteRN = new FabricanteRN();
	
	public List<Fabricante> getListaFabricante(){
		if(listaFabricante == null){
			listaFabricante = fabricanteRN.listarFabricante();
		}
		return listaFabricante;
	}
	
	public String salvar(){
		
		if(fabricante.getNome_fabricante() != null && fabricante.getNome_fabricante().length() >= 2){
			
			Locale locale = new Locale("pt", "BR");
			GregorianCalendar calendario = new GregorianCalendar();
			SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'", locale);
			fabricante.setData_cadastro(formatador.format(calendario.getTime()));
			
			fabricanteRN.salvar(fabricante);
			
			FacesMessage msg = new FacesMessage("Aviso", "Fabricante Salvo com Êxito!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			this.listaFabricante = null;
		}
		
		return "salvo";
	}
	
	public void alterar(){
		fabricanteRN.alterar(fabricante);
	}
	
	public void excluir(Fabricante f){
		fabricanteRN.deletar(f);
		this.listaFabricante = null;
	}
	
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public void setListaFabricante(List<Fabricante> listaFabricante) {
		this.listaFabricante = listaFabricante;
	}

}
