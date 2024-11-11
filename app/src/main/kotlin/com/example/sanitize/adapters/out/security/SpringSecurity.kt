package com.example.sanitize.adapters.out.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.invoke

@Configuration
@EnableWebSecurity
class SecurityConfig {

  @Bean
  fun filterChain(http: HttpSecurity): SecurityFilterChain {
    http {
      csrf {
        disable() // disable since it causes 403s and we are not gonna use sessions
      }
      authorizeHttpRequests {
        authorize("/**", permitAll)
      }
    }
    return http.build()
  }

}