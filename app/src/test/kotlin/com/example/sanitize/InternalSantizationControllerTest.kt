package com.example.sanitize

import com.example.sanitize.adapters.`in`.internal.dtos.CreateSensitiveWordRequest
import com.example.sanitize.adapters.`in`.internal.dtos.SensitiveWordDto
import com.example.sanitize.adapters.`in`.internal.dtos.SensitiveWordDto.Companion.toSensitiveWordDto
import com.example.sanitize.adapters.`in`.internal.dtos.UpdateSensitiveWordRequest
import com.example.sanitize.domain.ports.out.GetSensitiveWordsPort
import org.json.JSONObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity

@SpringBootTest(
  classes = [FlashHomeworkApplication::class],
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataJpa
class InternalSantizationControllerTest(
  @Autowired val restTemplate: TestRestTemplate,
  @Autowired private val getSensitiveWordsPort: GetSensitiveWordsPort,
  @Autowired private val mockSanitizationAdapter: MockSanitizationAdapter,
) {

  @BeforeEach
  fun beforeEach() {
    mockSanitizationAdapter.resetWords()
  }
  @Test
  fun `getWords - fail when not api key provided`() {
    val result = restTemplate.getForEntity<JSONObject>("/internal/words")

    result.statusCode shouldBe HttpStatus.FORBIDDEN

  }

  @Test
  fun `addSensitiveWords - fail when not api key provided`() {
    val result = restTemplate.getForEntity<JSONObject>("/internal/words")

    result.statusCode shouldBe HttpStatus.FORBIDDEN

  }

  @Test
  fun `updateSensitiveWords - fail when not api key provided`() {
    val result = restTemplate.exchange<JSONObject>(RequestEntity.put("/internal/words/0").build())

    result.statusCode shouldBe HttpStatus.FORBIDDEN

  }

  @Test
  fun `removeSensitiveWords - fail when not api key provided`() {
    val result = restTemplate.exchange<JSONObject>(RequestEntity.get("/internal/words/0").build())

    result.statusCode shouldBe HttpStatus.FORBIDDEN

  }

  @Test
  fun `getWords - success when api key provided`() {
    val result = restTemplate.exchange<List<SensitiveWordDto>>(RequestEntity.get("/internal/words").headers(HttpHeaders().apply {
      add("X-Internal-Api-Key", "test-internal-key")
      add("X-Api-Key", "test-key")
    }).build())

    result.statusCode shouldBe HttpStatus.OK
    result.body!! shouldBe getSensitiveWordsPort.getAllSensitiveWords().getOrThrow().map { it.toSensitiveWordDto() }
  }

  @Test
  fun `addSensitiveWords - success when api key provided`() {
    val numWords = getSensitiveWordsPort.getAllSensitiveWords().getOrThrow().size
    val result = restTemplate.exchange<List<SensitiveWordDto>>(RequestEntity.post("/internal/words").headers(HttpHeaders().apply {
      add("X-Internal-Api-Key", "test-internal-key")
    }).body(listOf(CreateSensitiveWordRequest(text = "added"))))

    result.statusCode shouldBe HttpStatus.NO_CONTENT
    getSensitiveWordsPort.getAllSensitiveWords().getOrThrow()[numWords].text shouldBe "added"
  }

  @Test
  fun `removeSensitiveWords - success when api key provided`() {
    val originalFirstWord = getSensitiveWordsPort.getAllSensitiveWords().getOrThrow()[0]
    val wordId = 0
    val result = restTemplate.exchange<List<SensitiveWordDto>>(RequestEntity.delete("/internal/words/$wordId").headers(HttpHeaders().apply {
      add("X-Internal-Api-Key", "test-internal-key")
    }).build())

    result.statusCode shouldBe HttpStatus.OK
    result.body!!.single() shouldBe originalFirstWord.toSensitiveWordDto()
    getSensitiveWordsPort.getAllSensitiveWords().getOrThrow().none { it.id == 0L } shouldBe true
    
  }

  @Test
  fun `updateSensitiveWords - success when api key provided`() {
    val original = getSensitiveWordsPort.getAllSensitiveWords().getOrThrow()[0].text
    val newValue = "changed"
    val result = restTemplate.exchange<SensitiveWordDto>(RequestEntity.put("/internal/words/0").headers(HttpHeaders().apply {
      add("X-Internal-Api-Key", "test-internal-key")
    }).body(UpdateSensitiveWordRequest(newValue = newValue)))

    result.statusCode shouldBe HttpStatus.OK
    val currentValue = getSensitiveWordsPort.getAllSensitiveWords().getOrThrow()[0].text
    original shouldNotBe newValue
    currentValue shouldBe newValue
    result.body!!.text shouldBe newValue
  }

}