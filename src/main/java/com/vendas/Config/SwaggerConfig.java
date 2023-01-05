package com.vendas.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
public class SwaggerConfig {

	// O Swagger so precisa de um método que retorna um Docket para montar a Doc

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false).select()			
				.apis(RequestHandlerSelectors.basePackage("com.vendas.Controller"))
				.build()
				.apiInfo(apiInfo());

	};

	private Contact contact() {
		return new Contact("Maurício Zatt", "https://www.linkedin.com/in/mauricio-zatt-04a759178/",
				"Mauriciozatt@hotmail.com");
	};

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder()
				.title("API de vendas")
				.description("Descrição Api de vendas")
				.version("1.0")
				.contact(contact())
				.build();
	};

}
