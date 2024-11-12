package com.example.sanitize.domain.ports.out

import com.example.sanitize.domain.models.SensitiveWord

interface GetSensitiveWordsPort {
  fun getAllSensitiveWords(): Result<List<SensitiveWord>>
  fun getSensitiveWords(wordIds: List<Long>): Result<List<SensitiveWord>>
}