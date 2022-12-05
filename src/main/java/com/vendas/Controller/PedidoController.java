package com.vendas.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.Model.Pedidos;
import com.vendas.Services.PedidosService;
import com.vendas.dto.PedidoDTO;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidosService service;

	public PedidoController(PedidosService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer salvar(@RequestBody PedidoDTO dto) {
		Pedidos NovoPedido = service.salvar(dto);
		return NovoPedido.getId();
	}

}
