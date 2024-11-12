package com.example.sanitize.adapters.`in`.web

import com.example.sanitize.adapters.`in`.web.dtos.SanitzationRequest
import com.example.sanitize.adapters.`in`.web.dtos.SanitzationResponse
import com.example.sanitize.domain.ports.`in`.GetSensitiveWordsUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SantizationController(
  val getSensitiveWordsUseCase: GetSensitiveWordsUseCase,
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

    val sensitiveWords = getSensitiveWordsUseCase.getSensitiveWords(listOf()).getOrThrow() // TODO: handle exception
    return ResponseEntity.ok(SanitzationResponse(
      // assume case-insensitive
      // ignore if it's inside another word. e.g. don't redact "action" if word is "actions" using word boundary \\b
      text = request.text.replace(sensitiveWords.joinToString("|") { "\\b${it.text.replace("\"", "")}\\b" }
        .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))) {
        (0 until it.value.length).joinToString("") { "*" }
      }
    ))
  }
}