package com.example.sanitize.domain.ports.out

import com.example.sanitize.domain.models.SensitiveWord

interface ChangeSensitiveWordsPort {
  fun changeSensitiveWords(oldWord: SensitiveWord, newWord: SensitiveWord): Result<List<String>>
}