package com.example.sanitize

import com.example.sanitize.adapters.`in`.internal.dtos.CreateSensitiveWordRequest
import com.example.sanitize.adapters.`in`.internal.dtos.SensitiveWordDto
import com.example.sanitize.adapters.`in`.internal.dtos.SensitiveWordDto.Companion.toSensitiveWordDto
import com.example.sanitize.adapters.`in`.internal.dtos.UpdateSensitiveWordRequest
import com.example.sanitize.domain.ports.out.GetSensitiveWordsPort
import org.json.JSONObject
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
class SantizationControllerTest(
  @Autowired val restTemplate: TestRestTemplate,
  @Autowired private val getSensitiveWordsPort: GetSensitiveWordsPort,
) {

    @Test
    fun `getWords - success when api key provided`() {
        val result = restTemplate.exchange<SanitzationResponse>(RequestEntity.post("/sanitize").headers(HttpHeaders().apply {
        add("X-Api-Key", "test-key")
        }).body(SanitzationResponse(text = "this is a action string"))

        result.statusCode shouldBe HttpStatus.OK
        result.body!! shouldBe SanitzationResponse(text = "this is a ****** string")
    }

}