package com.vendas.Repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vendas.Model.Clientes;
import com.vendas.Model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {

//retona todos os pedidos do cliente passado como parâmetro ...
	Set<Pedidos> findByclienteId(Clientes cliente);

	
	@Query("select p from Pedidos p left join fetch p.itens where p.id = ?1")
	Optional<Pedidos> findByIdFetchItens(@Param("id") Integer id);
	
		
}
