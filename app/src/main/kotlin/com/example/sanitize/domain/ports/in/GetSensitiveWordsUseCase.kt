package com.example.sanitize.domain.ports.`in`

import com.example.sanitize.domain.models.SensitiveWord

interface GetSensitiveWordsUseCase {
  fun getSensitiveWords(): Result<List<SensitiveWord>>
}