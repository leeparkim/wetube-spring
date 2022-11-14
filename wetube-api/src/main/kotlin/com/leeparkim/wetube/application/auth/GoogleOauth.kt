package com.leeparkim.wetube.application.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GoogleOauth(
        @Value("\${spring.OAuth2.google.url}")
        private val GOOGLE_SNS_LOGIN_URL: String? = null,

        @Value("\${spring.OAuth2.google.client-id}")
        private val GOOGLE_SNS_CLIENT_ID: String? = null,

        @Value("\${spring.OAuth2.google.callback-url}")
        private val GOOGLE_SNS_CALLBACK_URL: String? = null,

        @Value("\${spring.OAuth2.google.client-secret}")
        private val GOOGLE_SNS_CLIENT_SECRET: String? = null,

        @Value("\${spring.OAuth2.google.scope}")
        private val GOOGLE_DATA_ACCESS_SCOPE: String? = null): SocialOauth {

    override fun getOauthRedirectURL(): String {
        val paramsMap = mapOf("client_id" to GOOGLE_SNS_CLIENT_ID,
                "redirect_uri" to GOOGLE_SNS_CALLBACK_URL,
                "scope" to GOOGLE_DATA_ACCESS_SCOPE,
                "response_type" to "code"
        )

        val params = paramsMap.map{
            (key, value) -> "$key=$value"
        }.joinToString("&")

        val redirectURL = "$GOOGLE_SNS_LOGIN_URL?$params";
        return redirectURL;
    }
}