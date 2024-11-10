package com.example.sanitize.domain.ports.in

interface GetSensitiveWordsUseCase {
  fun getSensitiveWords(words: List<String>): Result<Unit>
}