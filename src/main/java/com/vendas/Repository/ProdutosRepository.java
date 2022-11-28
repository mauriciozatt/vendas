package com.vendas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.Model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {

}
