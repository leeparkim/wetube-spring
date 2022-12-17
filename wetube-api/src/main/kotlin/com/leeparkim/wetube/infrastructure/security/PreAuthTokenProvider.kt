package com.leeparkim.wetube.infrastructure.security

import com.leeparkim.wetube.application.auth.TokenService
import com.leeparkim.wetube.domain.user.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken


class PreAuthTokenProvider : AuthenticationProvider {
    @Autowired
    lateinit var userRepository: UserRepository;

    @Autowired
    lateinit var jwtService: TokenService<Long>

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        log.debug("authentication: ${SecurityContextHolder.getContext().authentication}")
        if (authentication is PreAuthenticatedAuthenticationToken) {
            val token = authentication.getPrincipal() as String
            val user = jwtService.decode(token)
                    ?.let { userRepository.findByIdOrNull(it) }
                    ?: throw IllegalAccessException("회원정보가 없습니다. token: $token")
            return UsernamePasswordAuthenticationToken(
                    user.id,
                    "",
                    listOf(SimpleGrantedAuthority(ROLE_USER))
            )
        }
        throw TokenMissingException("Invalid token")
    }

    override fun supports(authentication: Class<*>): Boolean {
        return PreAuthenticatedAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
        const val ROLE_USER = "USER"
    }
}