package com.example.sanitize.adapters.out.security

import com.example.sanitize.domain.models.WordError
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.stream.Collectors


@RestControllerAdvice
class ControllerAdvisor{

  @ExceptionHandler(Throwable::class)
  fun generalException(
    ex: Throwable, request: WebRequest
  ): Map<String, String> {
    return (mapOf(
      "timestamp" to LocalDateTime.now().toString(),
      "message" to (ex.message ?: "Internal Error"),
      "request-id" to (request.getHeader("X-Internal-Api-Key") ?: ""),
      "status" to "500",
    ))
  }

  @ExceptionHandler(WordError::class)
  fun wordError(
    ex: WordError, request: WebRequest, headers: HttpHeaders
  ): Map<String, String> {

    return (mapOf(
      "timestamp" to LocalDateTime.now().toString(),
      "message" to ex.message,
      "request-id" to (request.getHeader("X-Api-Key") ?: ""),
      "status" to "400",
    ))
  }

}