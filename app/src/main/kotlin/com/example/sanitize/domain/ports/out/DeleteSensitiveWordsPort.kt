package com.example.sanitize.domain.ports.out

import com.example.sanitize.domain.models.SensitiveWord

interface DeleteSensitiveWordsPort {
  fun deleteSensitiveWords(wordIds: List<Long>): Result<List<SensitiveWord>>
}