package com.vendas.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.Model.Clientes;
import com.vendas.Model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {

//retona todos os pedidos do cliente passado como parâmetro ...
	Set<Pedidos> findByclienteId(Clientes cliente);

}
