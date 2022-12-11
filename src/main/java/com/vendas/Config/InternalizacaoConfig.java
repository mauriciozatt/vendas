package com.vendas.Config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class InternalizacaoConfig {

	/*
	 * (Fonte de dados da MSN) Crio um método q me retorna um MessageSource, crio
	 * uma istancia da classe ReloadableResourceBundleMessageSource passando o meu
	 * arquivo e outras config e depois retorno o MessageSource configurado
	 */

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ResourceBundle = new ReloadableResourceBundleMessageSource();
		ResourceBundle.setBasename("classpath:messagens");
		ResourceBundle.setDefaultEncoding("ISO-8859-1");
		ResourceBundle.setDefaultLocale(Locale.getDefault());
		return ResourceBundle;
	};

	
	
	
	/*
	 * método que faz interpolação, pegando o meu MessageSource e trocando as
	 * menssagens definidas no .properties
	 */
	
	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBeanTroca() {

		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource());
		return localValidatorFactoryBean;

	}

}
