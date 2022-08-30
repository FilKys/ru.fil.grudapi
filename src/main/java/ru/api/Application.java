package ru.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ListVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@SpringBootApplication
public class Application {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(
                        "Test GRUD API",
                        "Тестовый проект с GRUD API",
                        "1.0.0",
                        "",
                        "Filipp Kusakin GitHub: https://github.com/FilKys",
                        "",
                        ""
                ))
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.api.controller"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
