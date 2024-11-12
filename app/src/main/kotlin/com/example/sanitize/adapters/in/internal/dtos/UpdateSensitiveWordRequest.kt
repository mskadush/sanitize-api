package com.example.sanitize.adapters.`in`.internal.dtos

data class UpdateSensitiveWordRequest(
  val currentValue: SensitiveWordDto,
  val newValue: SensitiveWordDto,
)