package com.vendas.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.vendas.Validation.NotEmptyList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO serve para mapear o objeto com apenas as propriedade que preciso
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

	@NotNull(message = "{campo.codigo.cliente.obrigatorio}")
	private Integer cliente;

	@NotNull(message = "{campo.total.pedido.obrigatorio}")
	private BigDecimal total;
	
	@NotEmptyList(message = "{campo.itens.pedido.obrigatorio}")
	private List<ItemPedidoDTO> itens;

}
