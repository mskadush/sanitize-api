package com.example.sanitize.adapters.`in`.web

import com.example.sanitize.adapters.`in`.internal.InternalSantizationController
import com.example.sanitize.adapters.`in`.web.dtos.SanitzationRequest
import com.example.sanitize.adapters.`in`.web.dtos.SanitzationResponse
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SantizationController(
  val internalSantizationController: InternalSantizationController,
) {

  // TODO: Rest annotations
  // TODO: Swagger annotation
  @PostMapping("/sanitize")
  @Operation(description = "Sanitize given input based off set words to redact.")
  fun sanitize(
    @RequestBody request: SanitzationRequest
  ): ResponseEntity<SanitzationResponse> {

    val sensitiveWords = internalSantizationController.getSensitiveWords().body!!
    return ResponseEntity.ok(SanitzationResponse(
      // assume case-insensitive
      // ignore if it's inside another word. e.g. don't redact "action" if word is "actions"
      text = request.text.replace(sensitiveWords.joinToString("|") { it.text }.toRegex(RegexOption.IGNORE_CASE)) {
        (0 until it.value.length).joinToString("") { "*" }.replaceFirst("*", " ").replaceAfterLast("*", " ")
      }
    ))
  }
}