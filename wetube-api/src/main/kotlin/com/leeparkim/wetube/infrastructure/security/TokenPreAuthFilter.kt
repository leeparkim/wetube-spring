package com.leeparkim.wetube.infrastructure.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import org.springframework.util.StringUtils
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest

class TokenPreAuthFilter : AbstractPreAuthenticatedProcessingFilter() {
    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest): Any? {
        return resolveToken(request)
    }

    override fun getPreAuthenticatedCredentials(request: HttpServletRequest): Any? {
        return resolveToken(request)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER_NAME)
        if (bearerToken == null || bearerToken == "") {
            return null
        }
        val matcher = BEARER_TOKEN_PATTERN.matcher(bearerToken)
        return if (!matcher.matches()) {
            null
        } else matcher.group(1)
    }

    companion object {
        private val BEARER_TOKEN_PATTERN = Pattern.compile("[Bb]earer (.*)")
        private const val AUTHORIZATION_HEADER_NAME = "Authorization"
    }
}
