package com.vendas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.vendas.Model.Clientes;
import com.vendas.Repository.ClientesRepository;

@SpringBootApplication
public class VendasApplication {

	@Autowired
	private ClientesRepository clienteRepository;

	/// CommandLineRunner - executa o código na inicialização da aplicação...
	@Bean
	public CommandLineRunner init() {
		return args -> {

			System.out.println("Salvando Clientes");
			clienteRepository.save(new Clientes("Mauricio"));
			clienteRepository.save(new Clientes("Jonhi"));
			clienteRepository.save(new Clientes("Batata"));

			System.out.println(clienteRepository.existsByNome("Batata"));

			List<Clientes> listClientes = new ArrayList<Clientes>();
			listClientes = clienteRepository.findAll();

			System.out.println("Buscando por nome");
			List<Clientes> listClientes2 = clienteRepository.EncontraPorNome("tata");

			for (Clientes c2 : listClientes2) {
				System.out.println(c2.getNome());
			}

			System.out.println("Atualizando Clientes");
			for (Clientes c : listClientes) {
				c.setNome(c.getNome() + " Atualizado");
				clienteRepository.save(c);
			}

			System.out.println("removendo os Clientes");
			for (Clientes c : listClientes) {
				clienteRepository.delete(c);
			}

			listClientes = clienteRepository.findAll();
			System.out.println("Mostrando os clientes ");
			for (Clientes c : listClientes) {
				System.out.println(c.getNome());
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
