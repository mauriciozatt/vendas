package com.vendas.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.vendas.Model.Clientes;
import com.vendas.Repository.ClientesRepository;

import net.bytebuddy.implementation.bytecode.Throw;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private ClientesRepository clientesRepository;

	// Instaciar via contrutor o meu repository ele já injeta a dependência
	// necessária...
	public ClienteController(ClientesRepository clientesRepository) {
		super();
		this.clientesRepository = clientesRepository;
	}
	
	

	@GetMapping
	public List<Clientes> pesquisa(Clientes clienteFiltro) {

		// Para aplicar filtros crio um ExampleMatcher que são regras de filtro
		// depois crio um example, este irá mapear as propriedades que estão alimentadas
		// da minha entidade e aplicar as regras do ExampleMatcher

		ExampleMatcher matcherRegra = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);

		Example<Clientes> example = Example.of(clienteFiltro, matcherRegra);

		return clientesRepository.findAll(example);

	}

	@GetMapping("/{id}")
	public Clientes getClienteById(@PathVariable Integer id) {
//método orElseThrow retorna o meu cliente or senão retorna a exceção..... 
		return clientesRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	};

	// Anotation @RequestBody - basicamente irá converter meu json para a minha
	// entidade cliente

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Clientes salvar(@RequestBody Clientes cliente) {
		return clientesRepository.save(cliente);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable(value = "id") Integer id) {

		if (clientesRepository.existsById(id)) {
			clientesRepository.deleteById(id);

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado para exclusão");
		}

	}

	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody Clientes cliente) {

		if (clientesRepository.existsById(id)) {
			cliente.setId(id); 
			clientesRepository.save(cliente);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado para atualização");

	}

}
