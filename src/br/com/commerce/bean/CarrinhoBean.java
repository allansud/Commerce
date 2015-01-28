package br.com.commerce.bean;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.commerce.endereco.Endereco;
import br.com.commerce.endereco.EnderecoRN;
import br.com.commerce.itemvenda.ItemVenda;
import br.com.commerce.produto.Produto;
import br.com.commerce.util.ContextoUtil;
import br.com.uol.pagseguro.domain.Error;
import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;

@ManagedBean
@SessionScoped
public class CarrinhoBean {
	
	private Produto produto = new Produto();
	private ItemVenda itemVenda = new ItemVenda();
	private List<ItemVenda> itens = new ArrayList<ItemVenda>();
	Locale br = new Locale("pt", "BR");
	private String total;
	private String enderecoEscolhido;
	
	public void adicionaItem(Produto p){
		
		itemVenda.setProduto(p);
		itemVenda.setValor_unitario(p.getValor());
		itemVenda.setQuantidade(itens.size());
		
		itens.add(itemVenda);
		calculaTotal();
	}
	
	public static BigDecimal roundingValue(BigDecimal value, int scale){
		return value.setScale(scale, RoundingMode.HALF_UP);
	}
	
	public void pagar(){
		
		EnderecoRN enderecoRN = new EnderecoRN();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		Endereco endereco = enderecoRN.carrega(Long.parseLong(getEnderecoEscolhido()));
		
		PaymentRequest paymentRequest = new PaymentRequest();
		
		paymentRequest.setCurrency(Currency.BRL);
		
		paymentRequest.addItem(String.valueOf(itemVenda.getProduto().getCodigo()), itemVenda.getProduto().getDescricao(),
				new Integer(1), roundingValue(new BigDecimal(itemVenda.getProduto().getValor()), 2), new Long(itemVenda.getProduto().getPeso()), null);		
		
		paymentRequest.setShippingAddress(endereco.getCountry(), endereco.getState(), endereco.getCity(), endereco.getDistrict(),
				endereco.getPostalCode(), endereco.getStreet(), endereco.getNumber(), endereco.getComplement());
		
		paymentRequest.setSender(contextoBean.getUsuarioLogado().getNome(), contextoBean.getUsuarioLogado().getEmail());
		
		paymentRequest.setShippingType(ShippingType.PAC);
		
		paymentRequest.setReference("ASDA356");
		
		try {
			
			boolean onlyCheckOutCode = false;
			String paymentURL = paymentRequest.register(PagSeguroConfig.getAccountCredentials(), onlyCheckOutCode);
			
			//String paymentURL = paymentRequest.register(new AccountCredentials("freitas_allan@hotmail.com","6FD788BA09674EFF8E5F9005098CE744"));
			System.out.println(paymentURL);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(paymentURL);
			
		} catch (PagSeguroServiceException e) {
			Iterator<?> itr = e.getErrors().iterator();
			while (itr.hasNext()){
				Error error = (Error) itr.next();
		        System.out.println("Código do erro: " + error.getCode());  
		        System.out.println("Mensagem de erro: " + error.getMessage());  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void calculaTotal(){
		double total = 0d;
		for(ItemVenda i : itens){
			total += i.getProduto().getValor();
		}
		this.total = NumberFormat.getCurrencyInstance(br).format(total);
	}

	/**
	 * @return the itens
	 */
	public List<ItemVenda> getItens() {
		return itens;
	}

	/**
	 * @param itens the itens to set
	 */
	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}


	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/**
	 * @return the enderecoEscolhido
	 */
	public String getEnderecoEscolhido() {
		return enderecoEscolhido;
	}

	/**
	 * @param enderecoEscolhido the enderecoEscolhido to set
	 */
	public void setEnderecoEscolhido(String enderecoEscolhido) {
		this.enderecoEscolhido = enderecoEscolhido;
	}
}
