package com.vendas.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.vendas.enums.StatusPedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PEDIDOS")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedidos {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// Muitos pedidos para um cliente)
	// No join column somente defino o nome do meu campo que e FK do cliente na
	// tabela pedidos....

	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Clientes cliente;

	@Column(name = "Data_Pedido")
	private LocalDate dataPedido;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusPedido status;  
	

	@Column(name = "total", precision = 20, scale = 2)
	private BigDecimal total;

	// mappedBy por que eu não tenho nenhuma chave para a tabela itens_pedido na
	// classe pedido... Nesse caso eu passo a propriedade
	// que faz referência ao relacionamento lá na classe ItemPedido
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;


	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	} 

	
	
	
}
