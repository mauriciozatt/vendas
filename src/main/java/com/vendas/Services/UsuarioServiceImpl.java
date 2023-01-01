package com.vendas.Services;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.vendas.Exception.SenhaInvalidaException;
import com.vendas.Model.Usuarios;
import com.vendas.Repository.UsuariosRepository;

//objetivo da classe carregar o usuário....

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

//	private PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(); // Algoritno de criptografia da senha...
	// }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// busco meu usuário do banco
		Usuarios usuario = usuariosRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados!"));

		String[] vRoles;
		if (usuario.getAdmin()) {
			vRoles = new String[] { "ADMIN", "USER" };
		} else {
			vRoles = new String[] { "USER" };
		}

		// retorno meu usuário (UserDetails) atráves da classe Uses
		return User.builder().username(usuario.getLogin()).password(usuario.getSenha()).roles(vRoles).build();

	};

	public void autenticar(Usuarios usuarioAtual) {

		UserDetails vUserBanco = loadUserByUsername(usuarioAtual.getLogin());

		/*
		 * Vou verificar se a senha passada nesse usuário bate com a que está no banco
		 * criptografada
		 */
		Boolean vLogou = passwordEncoder.matches(usuarioAtual.getSenha(), vUserBanco.getPassword());

		if (!vLogou) {
			throw new SenhaInvalidaException();
		}

	};

	@Transactional
	public Usuarios salvar(@Valid Usuarios usuario) {
		return usuariosRepository.save(usuario);
	}

}
