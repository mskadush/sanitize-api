package com.example.sanitize.domain.ports.out

interface GetSensitiveWordsPort {
  fun getSensitiveWords(): Result<List<String>>
}