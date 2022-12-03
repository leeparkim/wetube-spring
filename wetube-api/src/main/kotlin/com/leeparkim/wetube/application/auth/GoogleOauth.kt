package com.leeparkim.wetube.application.auth

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.leeparkim.wetube.domain.user.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


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

    var mapper: ObjectMapper = ObjectMapper()

    data class GoogleOAuthToken(
            @JsonProperty("access_token") val access_token: String?,
            @JsonProperty("expires_in") val expires_in: Long,
            @JsonProperty("scope") val scope: String?,
            @JsonProperty("token_type") val token_type: String?,
            @JsonProperty("id_token") val id_token: String
    )

    data class GoogleUser(
            @JsonProperty("id") var id: String? = null,
            @JsonProperty("email") var email: String? = null,
            @JsonProperty("verified_email") var verifiedEmail: Boolean? = null,
            @JsonProperty("picture") var picture: String? = null,
    )

    override fun getOauthRedirectURL(): String {
        val paramsMap = mapOf("client_id" to GOOGLE_SNS_CLIENT_ID,
                "redirect_uri" to GOOGLE_SNS_CALLBACK_URL,
                "scope" to GOOGLE_DATA_ACCESS_SCOPE,
                "response_type" to "code"
        )

        val params = paramsMap.map{
            (key, value) -> "$key=$value"
        }.joinToString("&")

        val redirectURL = "$GOOGLE_SNS_LOGIN_URL?$params"
        return redirectURL
    }

    override fun getAccessToken(code: String): String? {
        val GOOGLE_TOKEN_REQUEST_URL = "https://oauth2.googleapis.com/token"
        val restTemplate = RestTemplate()
        val params: MutableMap<String, Any> = HashMap()

        params["code"] = code
        params["client_id"] = GOOGLE_SNS_CLIENT_ID!!
        params["client_secret"] = GOOGLE_SNS_CLIENT_SECRET!!
        params["redirect_uri"] = GOOGLE_SNS_CALLBACK_URL!!
        params["grant_type"] = "authorization_code"

        val responseEntity = restTemplate.postForEntity(GOOGLE_TOKEN_REQUEST_URL,
                params, String::class.java)

        if (responseEntity.statusCode == HttpStatus.OK) {
            val (access_token) = mapper.readValue(responseEntity.body, GoogleOAuthToken::class.java)
            return access_token
        }

        return null
    }

    override fun getSocialUserId(accessToken: String): String? {
        val GOOGLE_USERINFO_REQUEST_URL = "https://www.googleapis.com/oauth2/v1/userinfo"

        val headers = HttpHeaders()
        headers.add("Authorization", "Bearer $accessToken")

        val restTemplate = RestTemplate()
        val request = HttpEntity<Any?>(headers)
        val response: ResponseEntity<String> = restTemplate.exchange(
                GOOGLE_USERINFO_REQUEST_URL,
                HttpMethod.GET,
                request,
                String::class.java
        )

        val (id) = mapper.readValue(response.body, GoogleUser::class.java)
        return id
    }
}