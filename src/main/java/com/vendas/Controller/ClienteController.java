package com.vendas.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<Clientes> getClienteById1(@PathVariable(value = "id") Integer id) {
		Optional<Clientes> clienteRetornado = clientesRepository.findById(id);
		if (clienteRetornado.isPresent()) { // se o id está persistido no banco..
			return ResponseEntity.ok(clienteRetornado.get());
		} else
			return ResponseEntity.notFound().build();
	}

}
