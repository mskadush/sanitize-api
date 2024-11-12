package com.example.sanitize.adapters.`in`.web

import com.example.sanitize.adapters.`in`.web.dtos.SanitzationRequest
import com.example.sanitize.adapters.`in`.web.dtos.SanitzationResponse
import com.example.sanitize.domain.models.WordError
import com.example.sanitize.domain.ports.`in`.GetSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.SanitizeTextUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SantizationController(
  val sanitizeTextUseCase: SanitizeTextUseCase,
) {

  @PostMapping("/sanitize")
  @Operation(
    description = "Sanitize given input based off set words to redact.",
    parameters = [
      Parameter(name = "x-api-key", `in` = ParameterIn.HEADER, required = true)
    ]
  )
  fun sanitize(
    @RequestBody request: SanitzationRequest
  ): ResponseEntity<SanitzationResponse> {
    val sanitizedText = sanitizeTextUseCase.sanitizeText(request.text).getOrElse { throw WordError("Failed to sanitize text") } // TODO: handle exception
    return ResponseEntity.ok(SanitzationResponse(text = sanitizedText))
  }
}