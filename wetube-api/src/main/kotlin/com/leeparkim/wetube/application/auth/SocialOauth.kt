package com.leeparkim.wetube.application.auth

import com.leeparkim.wetube.domain.user.SocialType

interface SocialOauth {
    fun getOauthRedirectURL(): String
    fun getAccessToken(code: String): String?
    fun getSocialUserIdAndEmail(accessToken: String): Pair<String, String>?
    fun getType(): SocialType
}