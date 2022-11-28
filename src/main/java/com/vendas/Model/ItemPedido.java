package com.vendas.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDOS_ITENS")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	// muitos itens pedido para um pedido..... Ou seja o meu pedido pode estar com
	// vários itens de pedido
	@ManyToOne
	@JoinColumn(name = "PEDIDO_ID")
	private Pedidos pedido;

	// Muitos itens de pedido para um produto... Ou seja o meu produto pode estar em
	// vários itens de pedido
	@ManyToOne
	@JoinColumn(name = "PRODUTO_ID")
	private Produtos produto;

	@Column(name = "QUANTIDADE", precision = 20, scale = 2)
	private BigDecimal quantidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

}
