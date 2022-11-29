package com.vendas.Controller;

import java.util.Optional;

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

import com.vendas.Model.Clientes;
import com.vendas.Repository.ClientesRepository;

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

	@GetMapping(value = "/{id}")
	public ResponseEntity<Clientes> getClienteById1(@PathVariable Integer id) {
		Optional<Clientes> clienteRetornado = clientesRepository.findById(id);
		if (clienteRetornado.isPresent()) { // se o id está persistido no banco..
			return ResponseEntity.ok(clienteRetornado.get());
		} else
			return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Clientes> salvar(@RequestBody Clientes cliente) { // @RequestBody - basicamente irá converter
																			// meu json para a minha entidade cliente
		Clientes c = clientesRepository.save(cliente);
		return ResponseEntity.ok(c);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity deletar(@PathVariable(value = "id") Integer id) {

		if (clientesRepository.existsById(id)) {
			clientesRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else
			return ResponseEntity.notFound().build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity atualizar(@PathVariable Integer id, @RequestBody Clientes cliente) {

		if (clientesRepository.existsById(id)) {
			cliente.setId(id);
			clientesRepository.save(cliente);
			return ResponseEntity.noContent().build();
		} else
			return ResponseEntity.notFound().build();

	}

}
