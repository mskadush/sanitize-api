package com.example.sanitize.domain.services

import com.example.sanitize.domain.models.SensitiveWord
import com.example.sanitize.domain.models.WordError
import com.example.sanitize.domain.ports.`in`.*
import com.example.sanitize.domain.ports.out.ChangeSensitiveWordsPort
import com.example.sanitize.domain.ports.out.CreateSensitiveWordsPort
import com.example.sanitize.domain.ports.out.DeleteSensitiveWordsPort
import com.example.sanitize.domain.ports.out.GetSensitiveWordsPort
import com.example.sanitize.domain.requests.ChangeWordRequest
import org.springframework.stereotype.Component

@Component
class SanitizationService(
  private val getSensitiveWordsPort: GetSensitiveWordsPort,
  private val createSensitiveWordsPort: CreateSensitiveWordsPort,
  private val deleteSensitiveWordsPort: DeleteSensitiveWordsPort,
  private val changeSensitiveWordsPort: ChangeSensitiveWordsPort,
) : CreateSensitiveWordsUseCase, GetSensitiveWordsUseCase, DeleteSensitiveWordsUseCase, ChangeSensitiveWordsUseCase,
  SanitizeTextUseCase {

  override fun createSensitiveWords(words: List<String>): Result<List<SensitiveWord>> =
    createSensitiveWordsPort.createSensitiveWords(words)

  override fun getSensitiveWords(wordIds: List<Long>): Result<List<SensitiveWord>> {
    if (wordIds.isNotEmpty()) {
      return getSensitiveWordsPort.getSensitiveWords(wordIds)
    }
    return getSensitiveWordsPort.getAllSensitiveWords()
  }

  // TODO: Handle partial failures
  override fun deleteSensitiveWords(wordIds: List<Long>): Result<List<SensitiveWord>> =
    deleteSensitiveWordsPort.deleteSensitiveWords(wordIds = wordIds)

  // TODO: Handle partial failures
  override fun changeSensitiveWords(request: ChangeWordRequest): Result<SensitiveWord> =
    changeSensitiveWordsPort.changeSensitiveWords(request)

  override fun sanitizeText(text: String): Result<String> {
    val sensitiveWords =
      getSensitiveWordsPort.getAllSensitiveWords().getOrElse { throw WordError("Failed to get sensitive words.") }
    if (text.contains("throw me")) {
      throw WordError("Test failure")
    }
    // assume case-insensitive
    // ignore if it's inside another word. e.g. don't redact "action" if word is "actions" using word boundary \\b
    return Result.success(text.replace(sensitiveWords.joinToString("|") { "\\b${it.text.replace("\"", "")}\\b" }
      .toRegex(setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))) {
      (0 until it.value.length).joinToString("") { "*" }
    })

  }
}