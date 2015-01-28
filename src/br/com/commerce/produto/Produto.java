package br.com.commerce.produto;

import java.io.Serializable;
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
import javax.persistence.Table;

import br.com.commerce.categoria.Categoria;
import br.com.commerce.comentario.Comentario;
import br.com.commerce.fabricante.Fabricante;
import br.com.commerce.itemvenda.ItemVenda;
import br.com.commerce.vendedor.Vendedor;

@SuppressWarnings("serial")
@Entity
@Table(name = "produto")
public class Produto implements Serializable {
	
	@Id
	@GeneratedValue
	private Long codigo;
	@Column(nullable = false, length = 30)
	private String nome;
	@Column(nullable = false, length = 1000)
	private String descricao;
	@Column(nullable = false, length = 7, precision = 6, scale = 2)
	private double valor;
	private boolean promocao;
	@Column(length = 100)
	private String garantia;
	@Column(length = 6)
	private String peso;
	@Column(length = 8)
	private String comprimento;
	@Column(length = 8)
	private String altura;
	@Column(length = 8)
	private String largura;
	private String data_cadastro;
	private String foto1;
	private String foto2;
	private String foto3;
	private String foto4;
	
	@OneToMany(mappedBy = "produto", targetEntity = Comentario.class,
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comentario> comentarios;
	
	@OneToMany(mappedBy = "produto", targetEntity = ItemVenda.class,
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ItemVenda> itemVenda;
	
	@ManyToOne
	@JoinColumn(name = "vendedor_id", nullable = false)
	private Vendedor vendedor;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "fabricante_id", nullable = false)
	private Fabricante fabricante;

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the valor
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * @return the promocao
	 */
	public boolean isPromocao() {
		return promocao;
	}

	/**
	 * @param promocao the promocao to set
	 */
	public void setPromocao(boolean promocao) {
		this.promocao = promocao;
	}

	/**
	 * @return the garantia
	 */
	public String getGarantia() {
		return garantia;
	}

	/**
	 * @param garantia the garantia to set
	 */
	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	/**
	 * @return the peso
	 */
	public String getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(String peso) {
		this.peso = peso;
	}

	/**
	 * @return the comprimento
	 */
	public String getComprimento() {
		return comprimento;
	}

	/**
	 * @param comprimento the comprimento to set
	 */
	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}

	/**
	 * @return the altura
	 */
	public String getAltura() {
		return altura;
	}

	/**
	 * @param altura the altura to set
	 */
	public void setAltura(String altura) {
		this.altura = altura;
	}

	/**
	 * @return the largura
	 */
	public String getLargura() {
		return largura;
	}

	/**
	 * @param largura the largura to set
	 */
	public void setLargura(String largura) {
		this.largura = largura;
	}

	/**
	 * @return the data_cadastro
	 */
	public String getData_cadastro() {
		return data_cadastro;
	}

	/**
	 * @param data_cadastro the data_cadastro to set
	 */
	public void setData_cadastro(String data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	/**
	 * @return the foto1
	 */
	public String getFoto1() {
		return foto1;
	}

	/**
	 * @param foto1 the foto1 to set
	 */
	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}

	/**
	 * @return the foto2
	 */
	public String getFoto2() {
		return foto2;
	}

	/**
	 * @param foto2 the foto2 to set
	 */
	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	/**
	 * @return the foto3
	 */
	public String getFoto3() {
		return foto3;
	}

	/**
	 * @param foto3 the foto3 to set
	 */
	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}

	/**
	 * @return the foto4
	 */
	public String getFoto4() {
		return foto4;
	}

	/**
	 * @param foto4 the foto4 to set
	 */
	public void setFoto4(String foto4) {
		this.foto4 = foto4;
	}

	/**
	 * @return the comentarios
	 */
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
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
	 * @return the vendedor
	 */
	public Vendedor getVendedor() {
		return vendedor;
	}

	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the fabricante
	 */
	public Fabricante getFabricante() {
		return fabricante;
	}

	/**
	 * @param fabricante the fabricante to set
	 */
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
