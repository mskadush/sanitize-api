package com.example.sanitize.domain.services

import com.example.sanitize.domain.ports.out.GetSensitiveWordsPort
import com.example.sanitize.domain.ports.out.SaveSensitiveWordsPort

class SantizationService(
  val getSensitiveWordsPort: GetSensitiveWordsPort,
  val saveSensitiveWordsPort: SaveSensitiveWordsPort,
) {

}