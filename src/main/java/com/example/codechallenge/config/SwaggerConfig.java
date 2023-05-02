package com.example.codechallenge.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.codechallenge.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/rest/**"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Code Challenge")
                .description("Program in java APIs reference for message processing").version("1.0").build();
    }

}


