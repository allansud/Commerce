package br.com.commerce.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.commerce.recuperasenha.RecuperaSenha;
import br.com.commerce.recuperasenha.RecuperaSenhaRN;

@ManagedBean
@RequestScoped
public class RecuperaSenhaBean {
	
	private RecuperaSenha recuperaSenha = new RecuperaSenha();
	private RecuperaSenhaRN recuperaSenhaRN = new RecuperaSenhaRN();
	
	public String recuperaSenhaPerdida(){
		if (recuperaSenha.getEmail() != null) {
			if(recuperaSenhaRN.recuperaSenhaEmail(recuperaSenha.getEmail())){
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage fm = new FacesMessage("E-mail foi enviado contendo informações importantes sobre a recuperação de senha!");
				fc.addMessage(null, fm);
			}			
		}
		return "senha-enviada";
	}
	
	/**
	 * @return the recuperaSenha
	 */
	public RecuperaSenha getRecuperaSenha() {
		return recuperaSenha;
	}

	/**
	 * @param recuperaSenha the recuperaSenha to set
	 */
	public void setRecuperaSenha(RecuperaSenha recuperaSenha) {
		this.recuperaSenha = recuperaSenha;
	}
	
	
}
