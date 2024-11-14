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
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.math.log

@RestController
@RequestMapping("/internal")
class InternalSantizationController(
  private val getSensitiveWordsUseCase: GetSensitiveWordsUseCase,
  private val createSensitiveWordsUseCase: CreateSensitiveWordsUseCase,
  private val changeSensitiveWordsUseCase: ChangeSensitiveWordsUseCase,
  private val deleteSensitiveWordsUseCase: DeleteSensitiveWordsUseCase,
) {

  private val logger = KotlinLogging.logger {  }

  @PostMapping("/words")
  @Operation(
    parameters = [
      Parameter(name = "x-internal-api-key", `in` = ParameterIn.HEADER, required = true)
    ]
  )
  fun addSensitiveWords(
    @RequestBody request: List<CreateSensitiveWordRequest>
  ): ResponseEntity<Unit> {
    logger.debug { "Adding sensitive words '${request.map { it.text }.toList()}'" }
    createSensitiveWordsUseCase.createSensitiveWords(request.map { it.text })
    logger.debug { "Word added" }
    return ResponseEntity.noContent().build()
  }

  @DeleteMapping("/words/{wordId}")
  @Operation(
    parameters = [
      Parameter(name = "x-internal-api-key", `in` = ParameterIn.HEADER, required = true)
    ]
  )
  fun removeSensitiveWords(
    @PathVariable wordId: Long,
  ): ResponseEntity<List<SensitiveWordDto>> {
    logger.debug { "Removing sensitive word with id $wordId" }
    val deletedWords = deleteSensitiveWordsUseCase.deleteSensitiveWords(listOf(wordId)).getOrElse {
      throw WordError("Failed to delete words.").also {
        logger.error { it.message }
      }
    }
    logger.debug { "Words removed. Returning removed words" }
    return ResponseEntity.ok(deletedWords.map { it.toSensitiveWordDto() })
  }

  @GetMapping("/words")
  @Operation(
    parameters = [
      Parameter(name = "x-internal-api-key", `in` = ParameterIn.HEADER, required = true)
    ]
  )
  fun getSensitiveWords(): ResponseEntity<List<SensitiveWordDto>> {
    logger.debug { "Getting sensitive words" }
    return ResponseEntity.ok(getSensitiveWordsUseCase.getSensitiveWords(listOf()).getOrElse {
      throw WordError("Failed to delete words.").also {
        logger.error { it.message }
      }
    }.map {
      it.toSensitiveWordDto()
    }).also {
      logger.debug { "Got words" }
    }
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
    logger.debug { "Updating sensitive word '$wordId' ${request.newValue}" }
    val word = changeSensitiveWordsUseCase.changeSensitiveWords(request = ChangeWordRequest(wordId = wordId, request.newValue)).getOrElse {
      throw WordError("Failed to delete words.").also {
        logger.error { it.message }
      }
    }
    logger.debug { "Updated words" }
    return ResponseEntity.ok(word.toSensitiveWordDto())
  }
}