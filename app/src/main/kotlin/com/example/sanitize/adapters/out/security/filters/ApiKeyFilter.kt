package com.example.sanitize.adapters.out.security.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

// TODO: move auth to own module
class ApiKeyFilter : OncePerRequestFilter() {


  private val kLogger = KotlinLogging.logger { }

  // TODO: use DB
  private val keys = mapOf(
    "test-key" to ApiUser(key = "test-key", role = "API_USER"),
    "test-internal-key" to ApiUser(key = "test-internal-key", role = "INTERNAL_API_USER"),
  )

  data class ApiUser(
    val key: String,
    val role: String,
  )

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    kLogger.debug { "Validating request api key" }
    val authorities = mutableListOf<GrantedAuthority>()
    getRoleForApiKey(request.getHeader("X-Api-Key") ?: "")?.let {
      kLogger.debug { "Found public API key. adding role" }
      authorities.add(SimpleGrantedAuthority(it))
    }
    getRoleForApiKey(request.getHeader("X-Internal-Api-Key") ?: "")?.let {
      kLogger.debug { "Found internal API key. adding role" }
      authorities.add(SimpleGrantedAuthority(it))
    }
    val context = SecurityContextHolder.createEmptyContext()
    context.authentication = UsernamePasswordAuthenticationToken("", "", authorities)

    SecurityContextHolder.setContext(context)

    filterChain.doFilter(request, response)
  }

  // TODO: handle this using API keys in DB and get role attached to API key
  private fun getRoleForApiKey(apiKey: String): String? = keys[apiKey]?.role
}