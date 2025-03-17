package org.example

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@OpenAPIDefinition(
    info = Info(
        title = "Account microservice REST API Documentation",
        description = "Account microservice REST API Documentation",
        version = "v1",
        contact = Contact(
            name = "Marcin Jóźwiak",
            email = "marcin@gmail.com"),
        license = License(
            name = "Apache 2.0")
    )
)
@EnableFeignClients
class AccountApplication

fun main(args: Array<String>) {
    runApplication<AccountApplication>(*args)
}