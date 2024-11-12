package com.example.sanitize.adapters.`in`.internal.dtos

import com.example.sanitize.domain.models.SensitiveWord

data class SensitiveWordDto(
  val id: Long,
  val text: String,
) {
  companion object {
    fun SensitiveWord.toSensitiveWordDto() = SensitiveWordDto(
      id = id,
      text = text,
    )
  }
}