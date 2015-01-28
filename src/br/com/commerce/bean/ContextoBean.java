package br.com.commerce.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.commerce.usuario.Usuario;
import br.com.commerce.usuario.UsuarioRN;

@ManagedBean(name = "contextoBean")
@SessionScoped
public class ContextoBean {

	private Usuario usuarioLogado = null;
	private Locale localizacao = null;
	private List<Locale> idioma = null;

	public Usuario getUsuarioLogado() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();

		if (this.usuarioLogado == null || !login.equals(this.usuarioLogado.getEmail())) {

			if (login != null) {
				UsuarioRN usuarioRN = new UsuarioRN();
				this.usuarioLogado = usuarioRN.buscaPorLogin(login.trim());
			}
		}
		return usuarioLogado;
	}
	
	public String getIP(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		return request.getRemoteAddr();
	}
	
	public List<Locale> getIdioma() {
		if(idioma == null){
			FacesContext context = FacesContext.getCurrentInstance();
			Iterator<Locale> locales = context.getApplication().getSupportedLocales();
			this.idioma = new ArrayList<Locale>();
			while (locales.hasNext()) {
				this.idioma.add(locales.next());
			}			
		}
		return idioma;
	}
	
	public void setIdiomaUsuario(String idioma) {
		if (idioma != null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.usuarioLogado = usuarioRN.carregar(this.getUsuarioLogado().getCodigo());
			this.usuarioLogado.setIdioma(idioma);
			usuarioRN.alterar(this.usuarioLogado);

			String[] info = idioma.split("_");
			Locale locale = new Locale(info[0], info[1]);

			FacesContext context = FacesContext.getCurrentInstance();
			context.getViewRoot().setLocale(locale);
		}
	}
	
	public void setIdiomaPagina(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		
		context.getViewRoot().setLocale(request.getLocale());
	}
	
	public Locale getLocaleUsuario() {
		if (this.localizacao == null) {
			Usuario usuario = this.getUsuarioLogado();
			String idioma = usuario.getIdioma();
			String[] info = idioma.split("_");
			this.localizacao = new Locale(info[0], info[1]);
		}
		return this.localizacao;
	}

	public void setUsuarioLogado(Usuario usuario) {
		this.usuarioLogado = usuario;
	}	
}
