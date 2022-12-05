package com.vendas.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vendas.Model.Clientes;
import com.vendas.Model.ItemPedido;
import com.vendas.Model.Pedidos;
import com.vendas.Model.Produtos;
import com.vendas.Repository.ClientesRepository;
import com.vendas.Repository.ItemPedidoRepository;
import com.vendas.Repository.PedidosRepository;
import com.vendas.Repository.ProdutosRepository;
import com.vendas.dto.ItemPedidoDTO;
import com.vendas.dto.PedidoDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // essa anotation gera um contrutor com todos os atributos obrigatórios (FINAL)
public class PedidoServiceImpl implements PedidosService {

	private final PedidosRepository pedidoRepository;
	private final ClientesRepository clientesRepository;
	private final ProdutosRepository produtosRepository;
	private final ItemPedidoRepository itemPedidoRepository;

	@Override
	@Transactional
	public Pedidos salvar(PedidoDTO dto) {
		Pedidos pedidoNovo = new Pedidos();

		// Popular meu pedido
		Clientes c = clientesRepository.findById(dto.getCliente())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));

		pedidoNovo.setCliente(c);

		pedidoNovo.setTotal(dto.getTotal());
		pedidoNovo.setDataPedido(LocalDate.now());

//pego meu DTO converto para uma lista de ItensPedido e salvo esses  itens... 
		List<ItemPedido> itens = ConverterItens(pedidoNovo, dto.getItens());

		pedidoRepository.save(pedidoNovo);

		itemPedidoRepository.saveAll(itens);

		pedidoNovo.setItens(itens);

		return pedidoNovo;
	};

	private List<ItemPedido> ConverterItens(Pedidos pedido, List<ItemPedidoDTO> itensDTO) {

		if (itensDTO.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossível salvar pedido sem Itens!");
		}

		// Vai percorer minha lista de ItemPedidoDTO para cada elemento add no DTO(dai
		// consigo manipular)
		return itensDTO.stream().map(dto -> {

			Produtos produto = produtosRepository.findById(dto.getProduto()).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto Inválido! " + dto.getProduto()));

			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setPedido(pedido);
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setProduto(produto);

			return itemPedido;

		}).collect(Collectors.toList());

	}

}