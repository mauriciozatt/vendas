package com.vendas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder ///Define que eu posso acessar meus atributos sem precisar instaciar um objeto 
public class InformacaoesPedidoDTO {
	
	private Integer codigo;  
	private String nomeCliente;
	private String data; 
	private BigDecimal total; 
	private List<InformacoesItemPedidoDTO> itens; 
	

}
