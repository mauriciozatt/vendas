package com.vendas.Controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vendas.Model.ItemPedido;
import com.vendas.Model.Pedidos;
import com.vendas.Services.PedidosService;
import com.vendas.dto.AtualizaStatusPedidoDTO;
import com.vendas.dto.InformacaoesPedidoDTO;
import com.vendas.dto.InformacoesItemPedidoDTO;
import com.vendas.dto.PedidoDTO;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidosService service;

	public PedidoController(PedidosService service) {
		this.service = service;
	};

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer salvar(@RequestBody @Valid PedidoDTO dto) {
		Pedidos NovoPedido = service.salvar(dto);
		return NovoPedido.getId();
	};

	@GetMapping("/{id}")
	public InformacaoesPedidoDTO retornaPedido(@PathVariable Integer id) {

		return service.obterPedidoCompleto(id).map(pedidoRetornado -> converter(pedidoRetornado))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!!!"));

	};

	private InformacaoesPedidoDTO converter(Pedidos pedido) {

		return InformacaoesPedidoDTO.builder().codigo(pedido.getId()).nomeCliente(pedido.getCliente().getNome())
				.data(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).total(pedido.getTotal())
				.status(pedido.getStatus().name()).itens(converterItens(pedido.getItens())).build();
		// .buid cria o objeto e já retorna pra mim sem a necessidade de eu instanciar
		// esse objeto ...
	};

	private List<InformacoesItemPedidoDTO> converterItens(List<ItemPedido> itens) {

		if (CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}

		// Vai percorer minha lista de ItemPedido para cada elemento add no "item"(dai
		// consigo manipular)
		return itens.stream().map(item ->

		InformacoesItemPedidoDTO.builder().descricaoProduto(item.getProduto().getDescricao())
				.preco(item.getProduto().getPreco()).quantidade(item.getQuantidade()).build()

		).collect(Collectors.toList());

	};

	@PatchMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void updatePedido(@PathVariable Integer id, @RequestBody AtualizaStatusPedidoDTO dto) {
		service.AtualizaStatusPedido(id, dto);
	}

};
