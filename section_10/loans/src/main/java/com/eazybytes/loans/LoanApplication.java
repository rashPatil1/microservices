package com.eazybytes.loans;

import com.eazybytes.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@OpenAPIDefinition(
		info = @Info(
				title = "Loan microservice REST API Documentation",
				description = "EazyBank Loan microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Rashmi Patil",
						email = "rashmi.patil@gmail.com",
						url = "https://github.com/rashPatil1"
				),
				license = @License(
						name = "XYZ 2.0",
						url = "https://github.com/rashPatil1"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "EazyBank Loan microservice REST API Documentation",
				url = "https://github.com/rashPatil1/swagger-ui.html"
		)
)
@EnableConfigurationProperties(value = LoansContactInfoDto.class)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}
