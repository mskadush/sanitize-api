package com.example.sanitize.adapters.out.persistence.jpa.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class SensitiveWordModel(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: String,
  val text: String,
)