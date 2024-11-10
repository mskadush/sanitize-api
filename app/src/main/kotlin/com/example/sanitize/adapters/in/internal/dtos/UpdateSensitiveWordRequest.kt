package com.example.sanitize.adapters.`in`.internal.dtos

data class UpdateSensitiveWordRequest(
  val currentValue: SensitiveWord,
  val newValue: SensitiveWord,
)