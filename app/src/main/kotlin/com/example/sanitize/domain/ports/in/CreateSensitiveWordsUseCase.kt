package com.example.sanitize.domain.ports.`in`

import com.example.sanitize.domain.models.SensitiveWord

interface CreateSensitiveWordsUseCase {
  fun createSensitiveWords(words: List<String>): Result<List<SensitiveWord>>
}