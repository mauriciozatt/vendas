package com.vendas.SecurityJwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vendas.Services.UsuarioServiceImpl;


/*
 * Filtro - Criado para interceptar as requisiçãos e obter o token do Header Authorization e adicionar o que e implementado aqui (Nesse
 * caso um usuário autenticado)
 */
public class JwtAuthFilter extends OncePerRequestFilter {

	private JWTServices services;
	private UsuarioServiceImpl usuarioServiceImpl;

	public JwtAuthFilter(JWTServices service, UsuarioServiceImpl usuarioServiceImpl) {
		this.services = service;
		this.usuarioServiceImpl = usuarioServiceImpl;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {


		String autorizacao = request.getHeader("Authorization");

		if ((autorizacao != null) && autorizacao.startsWith("Bearer")) {
			String token = autorizacao.split(" ")[1];

			Boolean tokenValido = services.validarToken(token);

			if (tokenValido) {
				String loginUsuario = services.obterLoginUsuario(token);

				UserDetails usuarioDetalhes = usuarioServiceImpl.loadUserByUsername(loginUsuario);

				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuarioDetalhes,
						null, usuarioDetalhes.getAuthorities());

				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(user);

			}		
		}
		
		filterChain.doFilter(request, response); 

	}

}
