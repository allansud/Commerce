package br.com.commerce.quote;

import java.util.List;

import br.com.commerce.dao.QuoteDAO;

public class QuoteRN {
	
	private QuoteDAO quoteDAO;
	
	public QuoteRN(){
		if(quoteDAO == null){
			quoteDAO = new QuoteDAO();
		}
	}
	
	public void salvar(Quote quote){
		quoteDAO.salvar(quote);
	}
	
	public void alterar(Quote quote){
		quoteDAO.alterar(quote);
	}
	
	public void excluir(Quote quote){
		quoteDAO.deletar(quote);
	}
	
	public List<Quote> listaQuotes(){
		return quoteDAO.getListaQuote();
	}
	
	public Quote quoteRandom(){
		return quoteDAO.getRandom();
	}
}