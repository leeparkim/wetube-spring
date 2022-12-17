package com.leeparkim.wetube.application.auth

interface SocialOauth {
    fun getOauthRedirectURL(): String
    fun getAccessToken(code: String): String?
    fun getSocialUserIdAndEmail(accessToken: String): Pair<String, String>?
}