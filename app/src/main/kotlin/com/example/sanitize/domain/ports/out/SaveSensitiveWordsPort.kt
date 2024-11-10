package com.example.sanitize.domain.ports.out

interface SaveSensitiveWordsPort {
  fun saveSensitiveWords(words: List<String>): Result<Unit>
}