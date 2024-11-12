package com.example.sanitize.domain.ports.`in`

import com.example.sanitize.domain.models.SensitiveWord
import com.example.sanitize.domain.requests.ChangeWordRequest

interface ChangeSensitiveWordsUseCase {
  fun changeSensitiveWords(request: ChangeWordRequest): Result<List<SensitiveWord>>
}