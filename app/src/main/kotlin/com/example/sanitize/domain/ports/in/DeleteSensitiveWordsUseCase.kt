package com.example.sanitize.domain.ports.`in`

interface DeleteSensitiveWordsUseCase {
  fun deleteSensitiveWords(wordIds: List<Long>): Result<List<String>>
}