package com.pedrohnf688.api.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private String PACOTE = "com.pedrohnf688.api.controller";
	private String TITULO = "Projeto Gerenciador de Projetos";
	private String DESCRICAO = "Documentação da API REST";
	private String NOME = "Pedro Henrique do Nascimento Fernandes";
	private String LINK_GITHUB = "https://github.com/pedrohnf688";
	private String EMAIL = "pedrohnf2014@gmail.com";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(PACOTE))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(TITULO).description(DESCRICAO).contact(new Contact(NOME, LINK_GITHUB, EMAIL))
				.version("1.0").build();
	}

}
