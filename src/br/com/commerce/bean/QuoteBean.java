package br.com.commerce.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.commerce.quote.Quote;
import br.com.commerce.quote.QuoteRN;

@ManagedBean
@RequestScoped
public class QuoteBean {
	
	private Quote quote = new Quote();
	private QuoteRN quoteRN = new QuoteRN();
	private List<Quote> lista;
	private String navegacao;
	
	public void salvar(){
		if(quote.getQuote() != null && quote.getQuote().length() > 12){
			quoteRN.salvar(quote);
			quote = new Quote();
		}else{						
			FacesMessage sms = new FacesMessage("Quote deve ter mais de 12 caracteres!");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, sms);
		}		
	}
	
	public void atualizar(){
		if(quote.getQuote() != null && quote.getQuote().length() > 12){
			quoteRN.alterar(quote);
		}else{
			FacesMessage sms = new FacesMessage("Não foi possível atualizar!");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, sms);
		}
	}
	
	public Quote randomQuote(){
		Quote q = null;
		try {
			q = quoteRN.quoteRandom();
		} catch (Exception e) {
			if(q == null){
				q = new Quote();
				q.setQuote("Do not go where the path may lead, go instead where there is no path and leave a trail. Ralph Waldo Emerson");
				quoteRN.salvar(q);
			}
		}
		return q;
	}
	
	public void excluir(){
		if(quote != null){
			quoteRN.excluir(quote);
		}else{
			FacesMessage sms = new FacesMessage("Não foi possível excluir!");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, sms);
		}
	}
	
	public String editar(){
		this.navegacao = "/admin/lista-quotes";
		return "/admin/editar-quotes";
	}
	
	public List<Quote> getLista(){
		if(lista == null)
			lista = quoteRN.listaQuotes();
		return lista;
	}

	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public String getNavegacao() {
		return navegacao;
	}

	public void setNavegacao(String navegacao) {
		this.navegacao = navegacao;
	}
}
