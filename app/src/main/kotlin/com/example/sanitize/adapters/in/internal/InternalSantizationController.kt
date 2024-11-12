package com.example.sanitize.adapters.`in`.internal

import com.example.sanitize.adapters.`in`.internal.dtos.SensitiveWordDto
import com.example.sanitize.adapters.`in`.internal.dtos.SensitiveWordDto.Companion.toSensitiveWordDto
import com.example.sanitize.adapters.`in`.internal.dtos.UpdateSensitiveWordRequest
import com.example.sanitize.domain.models.WordError
import com.example.sanitize.domain.ports.`in`.ChangeSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.GetSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.CreateSensitiveWordsUseCase
import com.example.sanitize.domain.requests.ChangeWordRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
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
  private val changeSensitiveWordsUseCase: ChangeSensitiveWordsUseCase,
) {

  // TODO: Rest annotations
  // TODO: Swagger annotation
  @PostMapping("/words")
  @Operation(
    description = "Sanitize given input based off set words to redact.",
    parameters = [
      Parameter(name = "x-internal-api-key", `in` = ParameterIn.HEADER, required = true)
    ]
  )
  fun addSensitiveWords(
    @RequestBody request: List<SensitiveWordDto>
  ): ResponseEntity<Unit> {
    createSensitiveWordsUseCase.createSensitiveWords(request.map { it.text })
    return ResponseEntity.noContent().build()
  }

  @DeleteMapping("/words")
  fun removeSensitiveWords(
    @RequestBody request: List<Long>
  ): ResponseEntity<Unit> {
    getSensitiveWordsUseCase.getSensitiveWords(word).getOrElse { throw WordError("Failed to get words.") }
    return ResponseEntity.noContent().build()
  }

  @GetMapping("/words")
  fun getSensitiveWords(
  ): ResponseEntity<List<SensitiveWordDto>> {
    return ResponseEntity.ok(getSensitiveWordsUseCase.getSensitiveWords(listOf()).getOrElse { throw WordError("Failed to get words.") }.map {
      it.toSensitiveWordDto()
    })
  }

  @PutMapping("/words")
  fun updateSensitiveWords(
    @RequestBody request: UpdateSensitiveWordRequest
  ): ResponseEntity<SensitiveWordDto> {
    val word = changeSensitiveWordsUseCase.changeSensitiveWords(request = ChangeWordRequest(request.currentValue.id, request.newValue.text)).getOrElse { throw WordError("Failed to update word") }
    return ResponseEntity.ok(word.toSensitiveWordDto())
  }
}