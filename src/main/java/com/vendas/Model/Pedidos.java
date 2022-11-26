package com.vendas.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDOS")
public class Pedidos {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// Muitos pedidos para um cliente)
//No join column somente defino o nome do meu campo que e FK do cliente na tabela pedidos....

	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Clientes clienteId;

	@Column(name = "Data_Pedido")
	private LocalDate dataPedido;

	@Column(name = "total", length = 20, precision = 2)
	private BigDecimal total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Clientes getClienteId() {
		return clienteId;
	}

	public void setClienteId(Clientes clienteId) {
		this.clienteId = clienteId;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
