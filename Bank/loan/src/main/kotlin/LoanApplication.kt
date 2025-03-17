package org.example

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(
    info = Info(
        title = "Loan microservice REST API Documentation",
        description = "Loan microservice REST API Documentation",
        version = "v1",
        contact = Contact(
            name = "Marcin Jóźwiak",
            email = "marcin@gmail.com"),
        license = License(
            name = "Apache 2.0")
    )
)
class LoanApplication

fun main(args: Array<String>) {
    runApplication<LoanApplication>(*args)
}