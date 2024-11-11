package com.example.sanitize.adapters.out.security.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.filter.OncePerRequestFilter


class ApiKeyFilter: OncePerRequestFilter() {

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

    if (isUserAllowed(request.getHeader("X-Api-Key"))) {
      filterChain.doFilter(request, response)
      return
    }
    throw AccessDeniedException("User not allowed")
  }

  private fun isUserAllowed(apiKey: String): Boolean = apiKey == "test-key"
}