package com.example.sanitize

import com.example.sanitize.adapters.`in`.web.dtos.SanitzationResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity

@SpringBootTest(
  classes = [FlashHomeworkApplication::class],
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SantizationControllerTest(
  @Autowired val restTemplate: TestRestTemplate,
  @Autowired private val mockSanitizationAdapter: MockSanitizationAdapter,
) {

    @BeforeEach
    fun beforeEach() {
        mockSanitizationAdapter.resetWords()
    }
    @Test
    fun `sanitize - success when api key provided`() {
        val result = restTemplate.exchange<SanitzationResponse>(RequestEntity.post("/sanitize").headers(HttpHeaders().apply {
        add("X-Api-Key", "test-key")
        }).body(SanitzationResponse(text = "this is a action string")))

        result.statusCode shouldBe HttpStatus.OK
        result.body!! shouldBe SanitzationResponse(text = "this is a ****** string")
    }

    @Test
    fun `sanitize - fail when api key provided`() {
        val result = restTemplate.exchange<Any>(RequestEntity.post("/sanitize").body(SanitzationResponse(text = "this is a action string")))

        result.statusCode shouldBe HttpStatus.FORBIDDEN
    }

}