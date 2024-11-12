package com.example.sanitize.adapters.`in`.internal

import com.example.sanitize.adapters.`in`.internal.dtos.SensitiveWordDto
import com.example.sanitize.adapters.`in`.internal.dtos.UpdateSensitiveWordRequest
import com.example.sanitize.domain.ports.`in`.GetSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.CreateSensitiveWordsUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internal")
class InternalSantizationController(
  private val getSensitiveWordsUseCase: GetSensitiveWordsUseCase,
  private val createSensitiveWordsUseCase: CreateSensitiveWordsUseCase,
) {

  // TODO: Rest annotations
  // TODO: Swagger annotation
  @PostMapping("/words")
  fun addSensitiveWords(
    @RequestBody request: List<SensitiveWordDto>
  ): ResponseEntity<Unit> {
    createSensitiveWordsUseCase.createSensitiveWords(request.map { it.text })
    return ResponseEntity.noContent().build()
  }

  @DeleteMapping("/words")
  fun removeSensitiveWords(
    @RequestBody request: List<SensitiveWordDto>
  ): ResponseEntity<Unit> {
//    sensitiveWords.removeIf { it in request.map { it.text } }
    return ResponseEntity.noContent().build()
  }

  @GetMapping("/words")
  fun getSensitiveWords(
  ): ResponseEntity<List<SensitiveWordDto>> {
    return ResponseEntity.ok(getSensitiveWordsUseCase.getSensitiveWords().getOrThrow().map {
      SensitiveWordDto(text = it)
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