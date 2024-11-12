package com.example.sanitize.domain.services

import com.example.sanitize.MockSanitizationAdapter
import com.example.sanitize.domain.models.SensitiveWord
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SanitizationServiceTest {

  private val mockRepo = MockSanitizationAdapter()
  private val service = SanitizationService(
    getSensitiveWordsPort = mockRepo,
    changeSensitiveWordsPort = mockRepo,
    createSensitiveWordsPort = mockRepo,
    deleteSensitiveWordsPort = mockRepo,
  )

  @Test
  fun createSensitiveWords() {
    val newWord = "new word"
    val result = service.createSensitiveWords(words = listOf(newWord))

    result.getOrThrow().single().copy(id = -1) shouldBe SensitiveWord(id = -1, newWord)
  }

  @Test
  fun `getSensitiveWords - get all words`() {
    val words = service.getSensitiveWords(listOf()).getOrThrow()

    words.first() shouldBe SensitiveWord(id = 0, "ACTION")
    words.last() shouldBe SensitiveWord(id = 227, "SELECT * FROM")
  }

  @Test
  fun deleteSensitiveWords() {
  }

  @Test
  fun changeSensitiveWords() {
  }

  @Test
  fun sanitizeText() {
  }

  private infix fun <T,U> T?.shouldBe(other: U) {
    assertEquals(other, this)
  }
}