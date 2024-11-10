package com.example.sanitize.adapters.`in`.web

import com.example.sanitize.adapters.`in`.web.dtos.SanitzationRequest
import com.example.sanitize.adapters.`in`.web.dtos.SanitzationResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SantizationController {

  // TODO: Rest annotations
  // TODO: Swagger annotation
  @PostMapping("/sanitize")
  fun sanitize(
    @RequestBody request: SanitzationRequest
  ): ResponseEntity<SanitzationResponse> {
    return ResponseEntity.ok(SanitzationResponse(
      text = request.text.replace("(test|string)".toRegex()) {
        (0 until it.value.length).joinToString("") { "*" }
      }
    ))
  }
}