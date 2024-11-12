package com.example.sanitize

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
@OpenAPIDefinition(
  servers = [
    Server(url = "https://localhost:8080", description = "HTTPS endpoint"),
    Server(url = "http://localhost:8080", description = "HTTP endpoint"),
  ],
  security = [ // TODO:
  ]
)
@SpringBootApplication
class FlashHomeworkApplication

fun main(args: Array<String>) {
  runApplication<FlashHomeworkApplication>(*args)
}
