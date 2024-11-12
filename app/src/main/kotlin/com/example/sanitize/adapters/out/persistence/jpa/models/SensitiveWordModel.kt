package com.example.sanitize.adapters.out.persistence.jpa.models

import com.example.sanitize.domain.models.SensitiveWord
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class SensitiveWordModel(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,
  val text: String,
) {
  fun toSensitiveWord() = SensitiveWord(
    id = id,
    text = text,
  )
}