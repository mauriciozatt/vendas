package com.vendas.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@Column(name = "total", precision = 20, scale = 2)
	private BigDecimal total;

	// mappedBy por que eu não tenho nenhuma chave para a tabela itens_pedido na
	// classe pedido... Nesse caso eu passo a propriedade
	// que faz referência ao relacionamento lá na classe ItemPedido
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;

	@Override
	public String toString() {
		return "Pedidos [id=" + id + ", dataPedido=" + dataPedido + ", total=" + total + "]";
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

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
