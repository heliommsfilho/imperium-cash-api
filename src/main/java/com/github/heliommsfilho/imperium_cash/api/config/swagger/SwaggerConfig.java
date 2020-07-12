package com.github.heliommsfilho.imperium_cash.api.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                       .groupName("imperium-cash-api")
                       .select().apis(RequestHandlerSelectors.any())
                                .paths(PathSelectors.any())
                                .build()
                       .apiInfo(apiInfo())
                       .directModelSubstitute(LocalDate.class, String.class)
                       .directModelSubstitute(LocalDateTime.class, String.class)
                       .genericModelSubstitutes(ResponseEntity.class);
                
    }
    
    private ApiInfo apiInfo() {
        ApiInfoBuilder builder = new ApiInfoBuilder();
        return builder.title("Imperium Cash API")
                      .description("API for Imperium Cash App")
                      .version("0.0.1-SNAPSHOT")
                      .build();
    }
}
