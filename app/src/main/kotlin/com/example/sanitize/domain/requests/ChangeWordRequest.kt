package com.example.sanitize.domain.requests

data class ChangeWordRequest(
  val wordId: Long,
  val newValue: String,
)
