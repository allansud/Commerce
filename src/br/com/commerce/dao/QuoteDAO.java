package br.com.commerce.dao;

import java.util.List;
import java.util.Random;

import br.com.commerce.quote.Quote;

public class QuoteDAO extends GenericDAO<Quote> {
	
	public void salvar(Quote quote){
		save(quote);
	}
	
	public void alterar(Quote quote){
		update(quote);
	}
	
	public void deletar(Quote quote){
		delete(quote);
	}
	
	public List<Quote> getListaQuote(){
		
		getSession().getTransaction().begin();
		List<Quote> q = null;
		try {
			q = findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(getSession() != null && getSession().isOpen()){
				getSession().close();
			}
		}
		return q;
	}
	
	public Quote getRandom(){
		
		List<Quote> lista = getListaQuote();
		int rdm = lista.size();				
		Quote q =  lista.get(new Random().nextInt(rdm));
		
		return q;
	}
}

