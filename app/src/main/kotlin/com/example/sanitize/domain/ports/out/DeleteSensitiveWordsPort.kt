package com.example.sanitize.domain.ports.out

interface DeleteSensitiveWordsPort {
  fun deleteSensitiveWords(wordIds: List<Long>): Result<List<String>>
}