package br.com.commerce.recuperasenha;

import br.com.commerce.usuario.Usuario;
import br.com.commerce.usuario.UsuarioRN;

public class RecuperaSenhaRN {
	
	private UsuarioRN usuarioRN = new UsuarioRN();
	
	public boolean recuperaSenhaEmail(String email){
		
		try {
			Usuario usuario = usuarioRN.buscaPorLogin(email);
			if(usuario != null){
				usuarioRN.enviarEmailSenha(usuario);
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
