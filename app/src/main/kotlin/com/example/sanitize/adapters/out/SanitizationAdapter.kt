package com.example.sanitize.adapters.out

import com.example.sanitize.adapters.out.persistence.jpa.models.SensitiveWordModel
import com.example.sanitize.adapters.out.persistence.jpa.models.SensitiveWordRepository
import com.example.sanitize.domain.models.SensitiveWord
import com.example.sanitize.domain.models.WordError
import com.example.sanitize.domain.ports.out.ChangeSensitiveWordsPort
import com.example.sanitize.domain.ports.out.GetSensitiveWordsPort
import com.example.sanitize.domain.ports.out.CreateSensitiveWordsPort
import com.example.sanitize.domain.ports.out.DeleteSensitiveWordsPort
import com.example.sanitize.domain.requests.ChangeWordRequest
import mu.KotlinLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class SanitizationAdapter(
  private val sensitiveWordRepository: SensitiveWordRepository,
): GetSensitiveWordsPort, CreateSensitiveWordsPort, DeleteSensitiveWordsPort, ChangeSensitiveWordsPort {

  private val logger = KotlinLogging.logger {  }
  override fun getAllSensitiveWords(): Result<List<SensitiveWord>> {
    logger.debug { "Getting all sensitive words" }
    return Result.success(sensitiveWordRepository.findAll().map { it.toSensitiveWord() }).also {
      logger.debug { "Got sensitive words" }
    }
  }

  override fun getSensitiveWords(wordIds: List<Long>): Result<List<SensitiveWord>> {
    logger.debug { "Getting sensitive words" }
    val words = sensitiveWordRepository.findAllById(wordIds.toMutableList())
    logger.debug { "Words pulled. converting to domain model" }
    return Result.success(words.map { it.toSensitiveWord() }).also {
      logger.debug { "Words converted, returning" }
    }
  }

  override fun createSensitiveWords(words: List<String>): Result<List<SensitiveWord>> {
    logger.debug { "Adding sensitive words" }
    val sensitiveWordsDocuments = sensitiveWordRepository.saveAll(words.map { SensitiveWordModel(text = it) })
    logger.debug { "Words added. converting to domain model" }
    return Result.success(sensitiveWordsDocuments.map { it.toSensitiveWord() }).also {
      logger.debug { "Words converted. returning" }
    }
  }

  override fun deleteSensitiveWords(wordIds: List<Long>): Result<List<SensitiveWord>> {
    logger.debug { "Deleting sensitive words" }
    val words = sensitiveWordRepository.findAllById(wordIds.toMutableList())
    logger.debug { "Words to be deleted found. deleting" }
    sensitiveWordRepository.deleteAllById(wordIds)
      logger.debug { "Words deleted" }
    return Result.success(words.map { it.toSensitiveWord() }).also {
      logger.debug { "Returning deleted words" }
    }
  }

  override fun changeSensitiveWords(request: ChangeWordRequest): Result<SensitiveWord> {
    logger.debug { "Updating sensitive words" }
    val currentWord = sensitiveWordRepository.findByIdOrNull(request.wordId) ?: return Result.failure(WordError("Word not found"))
    logger.debug { "Word found updating" }
    val newWord = sensitiveWordRepository.save(currentWord.copy(text = request.newValue))
      logger.debug { "Word updated. converting to domain model" }
    return Result.success(newWord.toSensitiveWord()).also {
      logger.debug { "Word converted. returning" }
    }
  }
}