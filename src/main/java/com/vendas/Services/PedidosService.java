package com.vendas.Services;

import java.util.Optional;

import com.vendas.Model.Pedidos;
import com.vendas.dto.AtualizaStatusPedidoDTO;
import com.vendas.dto.PedidoDTO;

public interface PedidosService {

	public Pedidos salvar(PedidoDTO dto);

	public Optional<Pedidos> obterPedidoCompleto(Integer id);
		
	public void AtualizaStatusPedido(Integer id, AtualizaStatusPedidoDTO dto); 

}