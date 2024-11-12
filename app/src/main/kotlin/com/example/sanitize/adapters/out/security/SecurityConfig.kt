package com.example.sanitize.adapters.out.security

import com.example.sanitize.adapters.out.security.filters.ApiKeyFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.access.intercept.AuthorizationFilter
import org.springframework.security.web.savedrequest.NullRequestCache

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
        authorize("/sanitize", hasAuthority("API_USER"))
        authorize("/internal/**", hasAuthority("INTERNAL_API_USER"))

        authorize("/swagger-ui/**", permitAll)
        authorize("/error", permitAll)
        authorize("/v3/api-docs/**", permitAll)
        authorize("/favicon.ico", permitAll)
      }
      requestCache {
        requestCache = NullRequestCache()
      }
    }
    http.addFilterBefore(ApiKeyFilter(), AuthorizationFilter::class.java)
    return http.build()
  }

}