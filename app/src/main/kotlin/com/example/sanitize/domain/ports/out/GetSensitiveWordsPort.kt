package com.example.sanitize.domain.ports.out

import com.example.sanitize.domain.models.SensitiveWord

interface GetSensitiveWordsPort {
  fun getSensitiveWords(): Result<List<SensitiveWord>>
  fun getSensitiveWords(wordId: Long): Result<List<SensitiveWord>>
}