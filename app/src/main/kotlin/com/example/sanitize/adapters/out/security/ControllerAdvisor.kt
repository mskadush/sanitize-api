package com.example.sanitize.adapters.out.security

import com.example.sanitize.domain.models.WordError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime


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
    ex: WordError, request: WebRequest
  ): Map<String, String> {

    return (mapOf(
      "timestamp" to LocalDateTime.now().toString(),
      "message" to ex.message,
      "request-id" to (request.getHeader("X-Api-Key") ?: ""),
      "status" to "400",
    ))
  }

}