package com.example.sanitize.adapters.`in`.internal

import com.example.sanitize.adapters.`in`.internal.dtos.CreateSensitiveWordRequest
import com.example.sanitize.adapters.`in`.internal.dtos.SensitiveWordDto
import com.example.sanitize.adapters.`in`.internal.dtos.SensitiveWordDto.Companion.toSensitiveWordDto
import com.example.sanitize.adapters.`in`.internal.dtos.UpdateSensitiveWordRequest
import com.example.sanitize.domain.models.WordError
import com.example.sanitize.domain.ports.`in`.ChangeSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.GetSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.CreateSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.DeleteSensitiveWordsUseCase
import com.example.sanitize.domain.requests.ChangeWordRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/internal")
class InternalSantizationController(
  private val getSensitiveWordsUseCase: GetSensitiveWordsUseCase,
  private val createSensitiveWordsUseCase: CreateSensitiveWordsUseCase,
  private val changeSensitiveWordsUseCase: ChangeSensitiveWordsUseCase,
  private val deleteSensitiveWordsUseCase: DeleteSensitiveWordsUseCase,
) {

  // TODO: Rest annotations
  // TODO: Swagger annotation
  @PostMapping("/words")
  @Operation(
    parameters = [
      Parameter(name = "x-internal-api-key", `in` = ParameterIn.HEADER, required = true)
    ]
  )
  fun addSensitiveWords(
    @RequestBody request: List<CreateSensitiveWordRequest>
  ): ResponseEntity<Unit> {
    createSensitiveWordsUseCase.createSensitiveWords(request.map { it.text })
    return ResponseEntity.noContent().build()
  }

  @DeleteMapping("/words")
  @Operation(
    parameters = [
      Parameter(name = "x-internal-api-key", `in` = ParameterIn.HEADER, required = true)
    ]
  )
  fun removeSensitiveWords(
    @RequestBody request: List<Long>
  ): ResponseEntity<List<SensitiveWordDto>> {
    val deletedWords = deleteSensitiveWordsUseCase.deleteSensitiveWords(request).getOrElse { throw WordError("Failed to delete words.") }
    return ResponseEntity.ok(deletedWords.map { it.toSensitiveWordDto() })
  }

  @GetMapping("/words")
  @Operation(
    parameters = [
      Parameter(name = "x-internal-api-key", `in` = ParameterIn.HEADER, required = true)
    ]
  )
  fun getSensitiveWords(): ResponseEntity<List<SensitiveWordDto>> {
    return ResponseEntity.ok(getSensitiveWordsUseCase.getSensitiveWords(listOf()).getOrElse { throw WordError("Failed to get words.") }.map {
      it.toSensitiveWordDto()
    })
  }

  @PutMapping("/words/{wordId}")
  @Operation(
    parameters = [
      Parameter(name = "x-internal-api-key", `in` = ParameterIn.HEADER, required = true)
    ]
  )
  fun updateSensitiveWords(
    @RequestBody request: UpdateSensitiveWordRequest, @PathVariable wordId: Long
  ): ResponseEntity<SensitiveWordDto> {
    val word = changeSensitiveWordsUseCase.changeSensitiveWords(request = ChangeWordRequest(wordId = wordId, request.newValue)).getOrElse { throw WordError("Failed to update word") }
    return ResponseEntity.ok(word.toSensitiveWordDto())
  }
}