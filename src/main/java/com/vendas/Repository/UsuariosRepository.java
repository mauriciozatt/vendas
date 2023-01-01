package com.vendas.Repository;

import java.util.Optional;
import com.vendas.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

	Optional<Usuarios> findByLogin(String login);

}
