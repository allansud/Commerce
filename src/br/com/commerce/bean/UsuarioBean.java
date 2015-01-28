package br.com.commerce.bean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.commerce.usuario.Usuario;
import br.com.commerce.usuario.UsuarioRN;
import br.com.commerce.util.ContextoUtil;

@ManagedBean
@RequestScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	UsuarioRN usuarioRN = new UsuarioRN();
	private String destinoSalvar;
	private List<Usuario> lista;
	
	public List<Usuario> getLista(){
		if(lista == null){
			lista = usuarioRN.listaUsuarios();
		}
		return lista;
	}
	
	public Usuario carregaUsuario(){
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		return this.usuario = contextoBean.getUsuarioLogado();
	}
	
	public String novo(){
		
		this.destinoSalvar = "usuarioSucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "usuario";
	}
	
	public String recuperaSenha(){
		return "recupera-senha";
	}
	
	public void alterar(){
		usuarioRN.alterar(usuario);
	}
	
	public void deletar(){
		usuarioRN.excluir(usuario);
	}
	
	public String salvar(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		String senha = usuario.getSenha();
		if(!senha.equals(this.confirmarSenha)){
			FacesMessage facesMessage = new FacesMessage("Senha não confere!");
			fc.addMessage(null, facesMessage);
			return null;
		}
		
		if(usuarioRN.verificaSeCadastro(this.usuario.getEmail())){
			FacesMessage fm = new FacesMessage("E-mail inserido já se encontra em nossa base de dados!");
			fc.addMessage(null, fm);
			return null;	
		}
		
		usuario.setData_cadastro(new Date());
		usuarioRN.salvar(usuario);
		
		if(this.destinoSalvar.equals("usuarioSucesso")){
			try {
				usuarioRN.enviarEmailPosCadastramento(usuario);
			} catch (Exception e) {
				FacesMessage facesMessage = new FacesMessage("Não foi possível enviar e-mail de cadastro!");
				fc.addMessage(null, facesMessage);
				return null;
			}
		}
		return this.destinoSalvar;
	}	
	
	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
