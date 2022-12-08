package com.vendas.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder ///Define que eu posso acessar meus atributos sem precisar instaciar um objeto 
public class InformacoesItemPedidoDTO {
	private String descricaoProduto;
	private BigDecimal preco; 
	private BigDecimal quantidade; 
	

}
