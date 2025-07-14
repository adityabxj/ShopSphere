package com.prac.product;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
					title = "Product Service Rest API Documentation",
					description = "Product service REST API's",
				version = "v1",
				contact = @Contact(
						name = "Aditya Tiwari",
						email = "aditya.tiwari@gmail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Sharepoint url Product Service API",
				url = "www.example.com"
		)
)
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProductApplication.class, args);

	}

}
