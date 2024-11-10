package com.example.sanitize.domain.services

import com.example.sanitize.domain.ports.`in`.GetSensitiveWordsUseCase
import com.example.sanitize.domain.ports.`in`.SaveSensitiveWordsUseCase
import com.example.sanitize.domain.ports.out.GetSensitiveWordsPort
import com.example.sanitize.domain.ports.out.SaveSensitiveWordsPort
import org.springframework.stereotype.Component

@Component
class SantizationService(
  private val getSensitiveWordsPort: GetSensitiveWordsPort,
  private val saveSensitiveWordsPort: SaveSensitiveWordsPort,
): SaveSensitiveWordsUseCase, GetSensitiveWordsUseCase {
  override fun saveSensitiveWords(words: List<String>): Result<Unit> {
    return saveSensitiveWordsPort.saveSensitiveWords(words)
  }

  override fun getSensitiveWords(): Result<List<String>> {
   return getSensitiveWordsPort.getSensitiveWords()
  }

}