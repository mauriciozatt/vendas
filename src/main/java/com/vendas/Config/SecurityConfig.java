package com.vendas.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vendas.SecurityJwt.JWTServices;
import com.vendas.SecurityJwt.JwtAuthFilter;
import com.vendas.Services.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	private JWTServices jwtServices;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Algoritno de criptografia da senha...
	}

	/*
	 * Autenticação em memória // criado um usuário mau com senha 123
	 * criptografada... protected void configure(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser(
	 * "mau") .password(passwordEncoder().encode("123")).roles("USER", "ADMIN"); }
	 */

	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtServices, usuarioServiceImpl);
	}

	// Autentica
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioServiceImpl).passwordEncoder(passwordEncoder());
	}

	// Autorizações....
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/api/clientes/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/api/pedidos/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/api/produtos/**")
				.hasRole("ADMIN").antMatchers(HttpMethod.POST, "/api/usuarios/**")
				.permitAll().anyRequest().authenticated()
				.and()// volta para a raiz HttpSecurity
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class); // Mando executar meu filtro
																							// antes do
																							// UsernamePasswordAuthenticationFilter
	}

}
