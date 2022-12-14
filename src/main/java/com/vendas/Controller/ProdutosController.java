package com.vendas.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vendas.Model.Produtos;
import com.vendas.Repository.ProdutosRepository;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutosController {

	private ProdutosRepository produtosRepository;

	public ProdutosController(ProdutosRepository produtosRepository) {
		this.produtosRepository = produtosRepository;
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<Produtos> pesquisa(Produtos produtoFiltro) {

		ExampleMatcher matcherRegra = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);

		Example<Produtos> example = Example.of(produtoFiltro, matcherRegra);

		return produtosRepository.findAll(example);

	}

	@GetMapping("/{id}")
	public Optional<Produtos> getProdutoById(@PathVariable Integer id) {

		if (produtosRepository.existsById(id)) {
			return produtosRepository.findById(id);

		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");
	};

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Produtos salvar(@RequestBody @Valid Produtos produto) {
		return produtosRepository.save(produto);
	}

	/*
	 * @PutMapping("/{id}")
	 * 
	 * @ResponseStatus(value = HttpStatus.NO_CONTENT) public void
	 * atualizar(@PathVariable Integer id, @RequestBody Produtos produto) {
	 * 
	 * if (produtosRepository.existsById(id)) { produtosRepository.save(produto); }
	 * else throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	 * "Produto não encontrado para atualização"); };
	 */

	/*
	 * Usando lambda ()->{} - vou retornar meu elemento para o parâmetro P, e dentro
	 * da {} posso manipular meu elemento P...
	 */

	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody @Valid Produtos produto) {

		produtosRepository.findById(id).map((p) -> {
			produto.setId(p.getId());
			return produtosRepository.save(produto);
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado para atualização"));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {

		if (produtosRepository.existsById(id)) {
			produtosRepository.deleteById(id);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado para exclusão");
	};

};
