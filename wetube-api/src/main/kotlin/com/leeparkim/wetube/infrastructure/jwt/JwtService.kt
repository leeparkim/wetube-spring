package com.leeparkim.wetube.infrastructure.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.leeparkim.wetube.application.auth.TokenService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class JwtService(
        @Value("\${jwt.token-issuer}")
        private val tokenIssuer: String,
        @Value("\${jwt.token-secret-key}")
        private val tokenSecretKey: String
) : TokenService<Long> {
    private val algorithm: Algorithm = Algorithm.HMAC256(tokenSecretKey)
    private val jwtVerifier: JWTVerifier = JWT.require(algorithm).build()

    override fun encode(userId: Long): String {
        return JWT.create()
                .withIssuer(tokenIssuer)
                .withClaim(CLAIM_NAME_USER_ID, userId)
                .sign(algorithm)
    }

    override fun decode(token: String?): Long? {
        return try {
            jwtVerifier.verify(token).let { it.claims[CLAIM_NAME_USER_ID]?.asLong() }
        } catch (ex: JWTVerificationException) {
            null
        }
    }

    companion object {
        private const val CLAIM_NAME_USER_ID = "userId"
    }

}