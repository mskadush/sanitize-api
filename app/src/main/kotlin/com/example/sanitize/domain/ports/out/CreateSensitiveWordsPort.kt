package com.example.sanitize.domain.ports.out

import com.example.sanitize.domain.models.SensitiveWord

interface CreateSensitiveWordsPort {
  fun createSensitiveWords(words: List<String>): Result<List<SensitiveWord>>
}