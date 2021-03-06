package br.com.queiroga.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	  @Bean
	  public Docket greetingApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("br.com.queiroga.stock.controller"))
	        .build()
	        .apiInfo(metaData());

	  }

	  private ApiInfo metaData() {
		    return new ApiInfoBuilder()
		        .title("Beverage stock API")
		        .description("api for stock management of a beverage warehouse")
		        .version("1.0.0")
		        .license("Apache License Version 2.0")
		        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
		        .build();
		  }

	
}
