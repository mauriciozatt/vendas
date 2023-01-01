package com.vendas.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USUARIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "LOGIN", length = 100)
	@NotEmpty(message = "{campo.login.obrigatorio}")
	private String login;

	@Column(name = "SENHA")
	@NotEmpty(message = "{campo.senha.obrigatorio}")
	private String Senha;

	@Column
	@Default
	private Boolean Admin = false;

}
