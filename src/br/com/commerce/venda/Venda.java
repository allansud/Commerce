package br.com.commerce.venda;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.commerce.itemvenda.ItemVenda;
import br.com.commerce.usuario.Usuario;

@SuppressWarnings("serial")
@Entity
public class Venda implements Serializable{

	@Id
	@GeneratedValue
	private int codigo;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = true)
	private Usuario usuario;
	@OneToMany(mappedBy = "venda", targetEntity = ItemVenda.class,
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ItemVenda> itemVenda;
	
	@Column(length = 15)
	private String tipo_venda;
	private Date data_da_venda;
	private double valor_total;
	@Column(length = 15)
	private long tracknumber;
	
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the itemVenda
	 */
	public List<ItemVenda> getItemVenda() {
		return itemVenda;
	}
	/**
	 * @param itemVenda the itemVenda to set
	 */
	public void setItemVenda(List<ItemVenda> itemVenda) {
		this.itemVenda = itemVenda;
	}
	/**
	 * @return the tipo_venda
	 */
	public String getTipo_venda() {
		return tipo_venda;
	}
	/**
	 * @param tipo_venda the tipo_venda to set
	 */
	public void setTipo_venda(String tipo_venda) {
		this.tipo_venda = tipo_venda;
	}
	/**
	 * @return the data_da_venda
	 */
	public Date getData_da_venda() {
		return data_da_venda;
	}
	/**
	 * @param data_da_venda the data_da_venda to set
	 */
	public void setData_da_venda(Date data_da_venda) {
		this.data_da_venda = data_da_venda;
	}
	/**
	 * @return the valor_total
	 */
	public double getValor_total() {
		return valor_total;
	}
	/**
	 * @param valor_total the valor_total to set
	 */
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	/**
	 * @return the tracknumber
	 */
	public long getTracknumber() {
		return tracknumber;
	}
	/**
	 * @param tracknumber the tracknumber to set
	 */
	public void setTracknumber(long tracknumber) {
		this.tracknumber = tracknumber;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
}
