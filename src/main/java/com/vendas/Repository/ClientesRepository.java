package com.vendas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vendas.Model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Integer> {

	// usando HQL
	@Query("select c from Clientes c where c.nome LIKE %?1%")
	List<Clientes> EncontraPorNome(@Param("nome") String nome);
		
	// usando SQL nativo....
	@Query(value = "select * from clientes where clientes.nome like %?1%", nativeQuery = true)
	List<Clientes> EncontraPorNomeNativo(@Param("nome") String nome);

	boolean existsByNome(String nome);

}
