package com.example.sanitize.domain.ports.`in`

interface GetSensitiveWordsUseCase {
  fun getSensitiveWords(): Result<List<String>>
}