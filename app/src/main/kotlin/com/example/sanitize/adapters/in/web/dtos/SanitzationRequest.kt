package com.example.sanitize.adapters.`in`.web.dtos

// using request since objects are better future proofing. In case different parameters are required for different redactions
data class SanitzationRequest(
  val text: String,
)
