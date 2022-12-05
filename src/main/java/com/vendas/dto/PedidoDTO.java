package com.vendas.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO serve para mapear o objeto com apenas as propriedade que preciso
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> itens;

}
