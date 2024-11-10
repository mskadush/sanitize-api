package com.example.sanitize.adapters.`in`.internal

import com.example.sanitize.adapters.`in`.internal.dtos.SensitiveWord
import com.example.sanitize.adapters.`in`.internal.dtos.UpdateSensitiveWordRequest
import com.example.sanitize.adapters.`in`.web.dtos.SanitzationResponse
import com.example.sanitize.domain.ports.`in`.GetSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.SaveSensitiveWordsUseCase
import com.example.sanitize.domain.ports.out.GetSensitiveWordsPort
import com.example.sanitize.domain.ports.out.SaveSensitiveWordsPort
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/internal")
class InternalSantizationController(
  private val getSensitiveWordsUseCase: GetSensitiveWordsUseCase,
  private val saveSensitiveWordsUseCase: SaveSensitiveWordsUseCase,
) {

  // TODO: Rest annotations
  // TODO: Swagger annotation
  @PostMapping("/words")
  fun addSensitiveWords(
    @RequestBody request: List<SensitiveWord>
  ): ResponseEntity<Unit> {
    saveSensitiveWordsUseCase.saveSensitiveWords(request.map { it.text })
    return ResponseEntity.noContent().build()
  }

  @DeleteMapping("/words")
  fun removeSensitiveWords(
    @RequestBody request: List<SensitiveWord>
  ): ResponseEntity<Unit> {
//    sensitiveWords.removeIf { it in request.map { it.text } }
    return ResponseEntity.noContent().build()
  }

  @GetMapping("/words")
  fun getSensitiveWords(
  ): ResponseEntity<List<SensitiveWord>> {
    return ResponseEntity.ok(getSensitiveWordsUseCase.getSensitiveWords().getOrThrow().map {
      SensitiveWord(text = it)
    })
  }

  @PutMapping("/words")
  fun updateSensitiveWords(
    @RequestBody request: UpdateSensitiveWordRequest
  ): ResponseEntity<Unit> {
//    val removed = sensitiveWords.removeIf { it == request.currentValue.text }
//    if (removed) {
//      sensitiveWords.add(request.newValue.text)
//    }
    return ResponseEntity.noContent().build()
  }
}