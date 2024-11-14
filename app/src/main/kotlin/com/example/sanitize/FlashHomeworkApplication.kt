package com.example.sanitize

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@OpenAPIDefinition(
  servers = [
    Server(url = "https://localhost:8080", description = "HTTPS endpoint"),
    Server(url = "http://localhost:8080", description = "HTTP endpoint"),
  ],
  security = [ // TODO:
  ]
)
@EnableJpaRepositories
@SpringBootApplication
class FlashHomeworkApplication

fun main(args: Array<String>) {
  runApplication<FlashHomeworkApplication>(*args)
}
