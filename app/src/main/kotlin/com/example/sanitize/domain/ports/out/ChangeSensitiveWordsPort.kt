package com.example.sanitize.domain.ports.out

import com.example.sanitize.domain.models.SensitiveWord
import com.example.sanitize.domain.requests.ChangeWordRequest

interface ChangeSensitiveWordsPort {
  fun changeSensitiveWords(request: ChangeWordRequest): Result<SensitiveWord>
}