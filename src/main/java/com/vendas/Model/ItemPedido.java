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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PEDIDOS_ITENS")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "PEDIDO_ID")
	private Pedidos pedido;

	@ManyToOne
	@JoinColumn(name = "PRODUTO_ID")
	private Produtos produto;

	@Column(name = "QUANTIDADE", precision = 20, scale = 2)
	private BigDecimal quantidade;

}
