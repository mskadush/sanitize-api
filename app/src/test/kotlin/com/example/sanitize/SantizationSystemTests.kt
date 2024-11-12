package com.example.sanitize

import com.example.sanitize.adapters.`in`.web.SantizationController
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder


@SpringBootTest
class SantizationSystemTests {

  @Autowired
  lateinit var mockMvc : MockMvc

  @BeforeEach
  fun setup() {
    // static import of MockMvcBuilders.standaloneSetup
    mockMvc = standaloneSetup(SantizationController(mockk()))
      .defaultRequest<StandaloneMockMvcBuilder>(get("/").accept(MediaType.APPLICATION_JSON))
      .alwaysExpect<StandaloneMockMvcBuilder>(status().isOk())
      .alwaysExpect<StandaloneMockMvcBuilder>(content().contentType("application/json;charset=UTF-8"))
      .build()
  }

  @Test
  fun test() {

  }
}