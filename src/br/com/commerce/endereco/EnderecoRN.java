package br.com.commerce.endereco;

import java.util.List;

import br.com.commerce.bean.ContextoBean;
import br.com.commerce.dao.EnderecoDAO;
import br.com.commerce.util.ContextoUtil;

public class EnderecoRN {
	
	private EnderecoDAO enderecoDAO;
	
	public EnderecoRN(){
		if(enderecoDAO == null){
			enderecoDAO = new EnderecoDAO();
		}
	}
	
	public void salvar(Endereco e){
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		e.setUsuario(contextoBean.getUsuarioLogado());
		enderecoDAO.save(e);
	}
	
	public void deletar(Endereco e){
		this.enderecoDAO.delete(e);
	}
	
	public void alterar(Endereco e){
		this.enderecoDAO.update(e);
	}
	
	public List<Endereco> listaPorUsuario(Long id){
		return this.enderecoDAO.enderecoPorUsuario(id);
	}
	
	public Endereco carrega(Long id){
		return this.enderecoDAO.findById(id);
	}
}
