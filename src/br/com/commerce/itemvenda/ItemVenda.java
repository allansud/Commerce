package br.com.commerce.itemvenda;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.commerce.produto.Produto;
import br.com.commerce.venda.Venda;

@SuppressWarnings("serial")
@Entity
public class ItemVenda implements Serializable{
	
	@Id
	@GeneratedValue
	private int codigo;
	@ManyToOne
	@JoinColumn(name = "prod_item_venda", nullable = true)
	private Produto produto;
	@ManyToOne
	@JoinColumn(name = "venda_ItemVenda", nullable = true)
	private Venda venda;
	private int quantidade;
	private double valor_unitario;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValor_unitario() {
		return valor_unitario;
	}
	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVenda other = (ItemVenda) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
}