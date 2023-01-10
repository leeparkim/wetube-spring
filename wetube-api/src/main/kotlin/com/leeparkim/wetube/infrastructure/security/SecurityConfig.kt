package com.leeparkim.wetube.infrastructure.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.leeparkim.wetube.domain.ResultCode
import com.leeparkim.wetube.presentation.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@EnableWebSecurity
@Configuration
class SecurityConfig(val objectMapper: ObjectMapper) {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/signin/**").permitAll()
                .antMatchers("/api/signin/**").permitAll()
                .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().hasRole(ROLE_USER)
        http.csrf().disable()
        http.logout().disable()
        http.formLogin().disable()
        http.httpBasic().disable()
        http.requestCache().disable()
        http.sessionManagement().disable()
        http.cors().configurationSource(corsConfigurationSource())
        http.addFilterAt(tokenPreAuthFilter(), AbstractPreAuthenticatedProcessingFilter::class.java)
        http.exceptionHandling()
                .authenticationEntryPoint { request: HttpServletRequest?,
                                            response: HttpServletResponse,
                                            authException: AuthenticationException? ->
                    log.debug("authenticationEntryPoint: {}, {}, {}", request, response, authException)
                    response.status = HttpStatus.UNAUTHORIZED.value()
                    response.contentType = MediaType.APPLICATION_JSON_VALUE
                    objectMapper.writeValue(
                            response.outputStream,
                            ApiResponse.failure<Unit>(ResultCode.UNAUTHORIZED)
                    )
                }.accessDeniedHandler { request, response, accessDeniedException ->
                    log.debug("accessDeniedHandler: {}, {}, {}", request, response, accessDeniedException)
                    response.status = HttpStatus.FORBIDDEN.value()
                    response.contentType = MediaType.APPLICATION_JSON_VALUE
                    objectMapper.writeValue(
                            response.outputStream,
                            ApiResponse.failure<Unit>(ResultCode.FORBIDDEN)
                    )
                }
        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.addAllowedOrigin("*")
        configuration.allowedMethods = listOf("HEAD", "GET", "POST", "PUT", "DELETE")
        configuration.addAllowedHeader("*")
        configuration.allowCredentials = true
        configuration.maxAge = 3600L
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }


    @Bean
    fun tokenPreAuthFilter(): TokenPreAuthFilter {
        val filter = TokenPreAuthFilter()
        filter.setAuthenticationManager(ProviderManager(preAuthTokenProvider()))
        return filter
    }

    @Bean
    fun preAuthTokenProvider() = PreAuthTokenProvider()

    companion object {
        val log: Logger = LoggerFactory.getLogger(SecurityConfig::class.java)
        const val ROLE_USER = "USER"
    }

}