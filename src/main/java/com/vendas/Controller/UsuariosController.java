package com.vendas.Controller;

import javax.annotation.Generated;
import javax.persistence.GenerationType;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vendas.Exception.SenhaInvalidaException;
import com.vendas.Model.Usuarios;
import com.vendas.SecurityJwt.JWTServices;
import com.vendas.Services.UsuarioServiceImpl;
import com.vendas.dto.CredenciaisDTO;
import com.vendas.dto.TokenDTO;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTServices Jwtservice;  
	

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Usuarios SalvarUsuario(@RequestBody @Valid Usuarios usuario) {

		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioServiceImpl.salvar(usuario);
	};
	
	
	
	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais ) {

		try {			
			 Usuarios vUsuarioComBaseNasCredencias = Usuarios.builder()
					 
						.login(credenciais.getLogin())
						.Senha(credenciais.getSenha()).build();
			
			
			 //Se não autenticar aqui estora as excptions e não cai no meu gerar token... 
			 usuarioServiceImpl.autenticar(vUsuarioComBaseNasCredencias);
			
			 
			 //problema aqui.....
			 String vToken =  Jwtservice.gerarToken(vUsuarioComBaseNasCredencias);
	
			return new TokenDTO(vUsuarioComBaseNasCredencias.getLogin(),vToken);					
					
								
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {			
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage()); 

		}
	
	};
	
	
		

}; 
