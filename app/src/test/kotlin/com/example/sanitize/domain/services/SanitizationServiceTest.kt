package com.example.sanitize.domain.services

import com.example.sanitize.MockSanitizationAdapter
import com.example.sanitize.domain.models.SensitiveWord
import com.example.sanitize.domain.requests.ChangeWordRequest
import com.example.sanitize.shouldBe
import com.example.sanitize.shouldNotBe
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
    val deletedWord = service.deleteSensitiveWords(listOf(0))

    deletedWord.getOrThrow().single() shouldBe SensitiveWord(id = 0, "ACTION")
  }

  @Test
  fun changeSensitiveWords() {
    val words = service.getSensitiveWords(listOf(0)).getOrThrow()
    val word = words.first()
    val newWord = "new word"

    val savedWord = service.changeSensitiveWords(request = ChangeWordRequest(wordId = 0L, newValue = newWord))

    savedWord.getOrThrow() shouldBe word.copy(text = newWord)
    service.getSensitiveWords(listOf()).getOrThrow().first() shouldNotBe word
  }

  @Test
  fun sanitizeText() {
    val result = service.sanitizeText("action is redacted")

    result.getOrThrow() shouldBe "****** is redacted"
  }

}