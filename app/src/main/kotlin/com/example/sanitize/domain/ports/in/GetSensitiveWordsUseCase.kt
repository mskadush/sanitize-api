package com.example.sanitize.domain.ports.`in`

import com.example.sanitize.domain.models.SensitiveWord

interface GetSensitiveWordsUseCase {
  fun getSensitiveWords(wordIds: List<Long>): Result<List<SensitiveWord>>
}