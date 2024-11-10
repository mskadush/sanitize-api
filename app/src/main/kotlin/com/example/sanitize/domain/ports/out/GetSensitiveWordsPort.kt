package com.example.sanitize.domain.ports.out

interface GetSensitiveWordsPort {
  fun getSensitiveWords(words: List<String>): Result<Unit>
}