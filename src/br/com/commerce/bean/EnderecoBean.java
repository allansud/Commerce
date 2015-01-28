package br.com.commerce.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.commerce.endereco.Endereco;
import br.com.commerce.endereco.EnderecoRN;
import br.com.commerce.util.ContextoUtil;

@ManagedBean
@ViewScoped
public class EnderecoBean {
	
	private Endereco endereco = new Endereco();
	EnderecoRN enderecoRN = new EnderecoRN();
	private List<Endereco> listarEndereco;
	ContextoBean contextoBean = ContextoUtil.getContextoBean();
	
	public List<Endereco> getListarEnderecoUsuario(){
		if(listarEndereco == null){
			listarEndereco = enderecoRN.listaPorUsuario(contextoBean.getUsuarioLogado().getCodigo());
		}
		return listarEndereco;
	}
	
	public void alterarEndereco(){
		enderecoRN.alterar(endereco);
	}
	
	public void excluirEndereco(Endereco e){
		this.enderecoRN.deletar(e);
		this.listarEndereco = null;
	}
	
	public void salvarEnderecos(){

		enderecoRN.salvar(endereco);
		this.endereco = new Endereco();
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage("Endereço Salvo com Sucesso!");
		fc.addMessage(null, fm);
	}

	/**
	 * @return the endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
