package com.example.sanitize.domain.services

import com.example.sanitize.domain.models.SensitiveWord
import com.example.sanitize.domain.ports.`in`.ChangeSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.GetSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.CreateSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.DeleteSensitiveWordsUseCase
import com.example.sanitize.domain.ports.out.ChangeSensitiveWordsPort
import com.example.sanitize.domain.ports.out.GetSensitiveWordsPort
import com.example.sanitize.domain.ports.out.CreateSensitiveWordsPort
import com.example.sanitize.domain.ports.out.DeleteSensitiveWordsPort
import com.example.sanitize.domain.requests.ChangeWordRequest
import org.springframework.stereotype.Component

@Component
class SanitizationService(
  private val getSensitiveWordsPort: GetSensitiveWordsPort,
  private val createSensitiveWordsPort: CreateSensitiveWordsPort,
  private val deleteSensitiveWordsPort: DeleteSensitiveWordsPort,
  private val changeSensitiveWordsPort: ChangeSensitiveWordsPort,
): CreateSensitiveWordsUseCase, GetSensitiveWordsUseCase, DeleteSensitiveWordsUseCase, ChangeSensitiveWordsUseCase {
  override fun createSensitiveWords(words: List<String>): Result<List<SensitiveWord>> {
    return createSensitiveWordsPort.createSensitiveWords(words)
  }

  override fun getSensitiveWords(): Result<List<SensitiveWord>> {
   return getSensitiveWordsPort.getSensitiveWords()
  }

  override fun deleteSensitiveWords(wordIds: List<Long>): Result<List<String>> {
    deleteSensitiveWordsPort.deleteSensitiveWords(
      wordIds = wordIds
    )
  }

  override fun changeSensitiveWords(request: ChangeWordRequest): Result<List<SensitiveWord>> {
    getSensitiveWordsPort.getSensitiveWords()
    TODO("Not yet implemented")
  }

}