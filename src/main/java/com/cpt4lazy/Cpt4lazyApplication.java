package com.cpt4lazy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class Cpt4lazyApplication {
	public static void main(String[] args) {
		SpringApplication.run(Cpt4lazyApplication.class, args);
	}
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cpt4lazy.controller"))
                .build()
                .apiInfo(apiDetails());
        
    }
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"CPT4Lazy API",
				"API docuemntation for CPT4Lazy Application",
				"1.0",
				"Free to use by MIU Student and Alumni",
				new springfox.documentation.service.Contact("CPT4Lazy Developers", "http://CPT4Lazy.com", "cpt4lazy@miu.com"),
				"API License",
				"http://CPT4Lazy.com",
				Collections.emptyList());
	}
}
