package com.vendas.SecurityJwt;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.vendas.Model.Usuarios;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//Classe resposável por codificar e decodificar o token... etc

@Service
public class JWTServices {

	final private long expiracao = 30;

	@Value("${security.jwt.assinatura}")
	private String chaveAssinatura;

	public String gerarToken(Usuarios usuario) {

		// pego a data do meu dia e somo mais minha expiração...

		LocalDateTime vDataHoraExpiracao = LocalDateTime.now().plusMinutes(expiracao);
		Instant vInstant = vDataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date vData = Date.from(vInstant);

		/*
		 * Aqui gero meu token.... Subjetc - informação qualquer que eu defino
		 * Expiration - data de expiração do token 
		 * sigWith - assinar com : passo o  algorítmo e minha chave 
		 * compact - converte para String...
		 */

		return Jwts.builder()
				.setSubject(usuario.getLogin())
				.setExpiration(vData)
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura)
				.compact();
	};
	
	

	/*
	 * Decodificando meu token .... Claims: são informações do meu token 
	 * parser: método da classe Jwts para decodificar o token passo a minha chave e meu  token
	 */

	private Claims obterClaims(String token) throws ExpiredJwtException {

		return Jwts.parser().setSigningKey(chaveAssinatura).parseClaimsJws(token).getBody();
	};
	
	
	

	public Boolean validarToken(String token) {
		try {

			// Pego a data do meu token...
			Date dataExpiracao = (Date) obterClaims(token).getExpiration();

			// Converto a data para LocalDateTime
			LocalDateTime dataExpiracaoConvertida = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

			return dataExpiracaoConvertida.isAfter(LocalDateTime.now(ZoneId.systemDefault()));
		

		} catch (Exception e) {
			return false;
		}

	};

	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return obterClaims(token).getSubject();
	};

	/*
	 * //testar token public static void main(String[] args) {
	 * ConfigurableApplicationContext contexto =
	 * SpringApplication.run(VendasApplication.class, args);
	 * 
	 * JWTServices service = contexto.getBean(JWTServices.class); Usuarios usuario =
	 * Usuarios.builder().login("moris").build(); String token =
	 * service.gerarToken(usuario);
	 * 
	 * System.out.println(token); System.out.println("Token e:" +
	 * service.validarToken(token)); System.out.println("Usuário:" +
	 * service.obterLoginUsuario(token));
	 * 
	 * };
	 */

}
