package com.example.sanitize.domain.ports.`in`

interface SanitizeTextUseCase {
  fun sanitizeText(text: String): Result<String>
}