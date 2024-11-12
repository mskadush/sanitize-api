package com.example.sanitize.domain.ports.`in`

import com.example.sanitize.domain.models.SensitiveWord

interface DeleteSensitiveWordsUseCase {
  fun deleteSensitiveWords(wordIds: List<Long>): Result<List<SensitiveWord>>
}