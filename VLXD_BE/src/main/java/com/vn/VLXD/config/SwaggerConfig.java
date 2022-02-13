package com.vn.VLXD.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Authorization;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2) 
	        .apiInfo(apiInfo())
	        .securityContexts(Arrays.asList(securityContext()))
	        .securitySchemes(Arrays.asList(apiKey()))
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build()
	          .groupName("SWAGGER-UI by Công Thắng");                                           
	    }
	 
	 private ApiInfo apiInfo() {
		 return new ApiInfo(
				 "SWAGGER-UI REST API",
				 "SWAGGER-UI by Công Thắng ",
				 "1.0",
				 "Terms of service",
				 new Contact("www.a.vn", "www.b.vn", "www.c.vn"),
				 "License of congthang.vn",
				 "www.d.vn",
				 Collections.emptyList());
	 }
	 private SecurityContext securityContext() {
		 return SecurityContext.builder().securityReferences(defaultAuth()).build();
	 }
	 
	 private List<SecurityReference> defaultAuth(){
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0]  =authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	 }
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
}
