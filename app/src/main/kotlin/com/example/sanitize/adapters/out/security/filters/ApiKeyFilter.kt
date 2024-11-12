package com.example.sanitize.adapters.out.security.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class ApiKeyFilter: OncePerRequestFilter() {

  // TODO: use DB
  private val keys = mapOf(
    "test-key".lowercase() to ApiUser(key = "test-key", role = "API_USER"),
    "tes-internal-key".lowercase() to ApiUser(key = "test-key", role = "INTERNAL_API_USER"),
  )
  data class ApiUser(
    val key: String,
    val role: String,
  )

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

    val role =
      getRoleForApiKey(request.getHeader("X-Api-Key") ?: request.getHeader("X-Internal-Api-Key") ?: "")
    if (role != null) {
      val context = SecurityContextHolder.createEmptyContext()
      context.authentication = UsernamePasswordAuthenticationToken("username", "password", mutableListOf(SimpleGrantedAuthority(role)))

      SecurityContextHolder.setContext(context)
      filterChain.doFilter(request, response)
      return
    }
    throw AccessDeniedException("User not allowed")
  }

  // TODO: handle this using API keys in DB and get role attached to API key
  private fun getRoleForApiKey(apiKey: String): String? = keys[apiKey]?.role
}