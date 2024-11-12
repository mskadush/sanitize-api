package com.example.sanitize.domain.models

// TODO: Prefer object over sending string in case additional rules apply to redaction.
data class SensitiveWord(
  val id: Long,
  val text: String,
)
