package com.example.sanitize.domain.ports.in

interface SaveSensitiveWordsUseCase {
  fun saveSensitiveWords(words: List<String>): Result<Unit>
}