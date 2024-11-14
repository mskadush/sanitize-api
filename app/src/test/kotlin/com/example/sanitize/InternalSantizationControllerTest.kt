package com.example.sanitize

import com.example.sanitize.adapters.out.SanitizationAdapter
import com.example.sanitize.domain.services.SanitizationService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@WebMvcTest
@Import(SanitizationService::class, SanitizationAdapter::class, TestcontainersConfiguration::class)
//@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages = ["com.example.sanitize.adapters.out.persistence.jpa.models"])
class InternalSantizationControllerTest(@Autowired val mockMvc: MockMvc) {

  @Test
  fun sanitize() {
    mockMvc.perform(get("/words"))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.bankCode").value("ING"));
  }

}