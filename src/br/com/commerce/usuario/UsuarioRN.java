package br.com.commerce.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.commerce.dao.UsuarioDAO;
import br.com.commerce.permissao.Permissao;
import br.com.commerce.util.EmailUtil;
import br.com.commerce.util.MensagemUtil;
import br.com.commerce.util.RNException;
import br.com.commerce.util.UtilException;

public class UsuarioRN {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioRN(){
		usuarioDAO = new UsuarioDAO();
	}
	
	public List<Usuario> listaUsuarios(){
		List<Usuario> listar = null;
		try {
			listar = usuarioDAO.findAll();
		} catch (Exception e) {
			usuarioDAO.getSession().getTransaction().rollback();
		}
		return listar;
	}
	
	public void salvar(Usuario usuario){
		
		Long id = usuario.getCodigo();
		if(id == null || id == 0){
			
			List<Permissao> permissoes = new ArrayList<Permissao>();
			Permissao p = new Permissao();
			p.setPermissao("ROLE_USUARIO");
			p.setUsuario(usuario);
			permissoes.add(p);
			usuario.setPermissoes(permissoes);
		}
		this.usuarioDAO.save(usuario);
	}
	
	public void alterar(Usuario usuario){
		usuarioDAO.update(usuario);
	}
	
	public void excluir(Usuario usuario){
		usuarioDAO.delete(usuario);
	}
	
	public boolean verificaSeCadastro(String email){
		return usuarioDAO.verificarCadastro(email);
	}
	
	public Usuario buscaPorLogin(String email){
		return usuarioDAO.buscarPorLogin(email);
	}
	
	public Usuario carregar(Long codigo){
		return usuarioDAO.carregar(codigo);
	}
	
	public void enviarEmailPosCadastramento(Usuario usuario) throws RNException {
		//Enviando um e-mail conforme o idioma do usuário
		String[] info = usuario.getIdioma().split("_");
		Locale locale = new Locale(info[0], info[1]);
			
		String titulo = MensagemUtil.getMensagem(locale, "email_titulo");
		String mensagem = MensagemUtil.getMensagem(locale, "email_mensagem", usuario.getNome(), usuario.getEmail(), usuario.getSenha());
		try {		
			EmailUtil emailUtil = new EmailUtil();
			emailUtil.enviarEmail(null, usuario.getEmail(), titulo, mensagem);
		} catch (UtilException e) {
			throw new RNException(e);
		}
	}
	
	public void enviarEmailSenha(Usuario usuario) throws RNException {
		//Enviando um e-mail conforme o idioma do usuário
		String[] info = usuario.getIdioma().split("_");
		Locale locale = new Locale(info[0], info[1]);
			
		String titulo = MensagemUtil.getMensagem(locale, "email_senha");
		String mensagem = MensagemUtil.getMensagem(locale, "email_mensagem_senha", usuario.getNome(), usuario.getEmail(), usuario.getSenha());
		try {		
			EmailUtil emailUtil = new EmailUtil();
			emailUtil.enviarEmail(null, usuario.getEmail(), titulo, mensagem);
		} catch (UtilException e) {
			throw new RNException(e);
		}
	}
}
