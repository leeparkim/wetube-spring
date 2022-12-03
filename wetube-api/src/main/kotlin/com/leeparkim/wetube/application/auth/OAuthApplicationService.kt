package com.leeparkim.wetube.application.auth

import com.leeparkim.wetube.domain.user.SocialType
import org.springframework.stereotype.Service


@Service
class OAuthApplicationService(var googleOauth: GoogleOauth) {

    fun getSocialAuth(socialLoginType: String): SocialOauth {
        val socialOauth: SocialOauth = when (SocialType.valueOf(socialLoginType.uppercase())) {
            SocialType.GOOGLE -> googleOauth
            else -> {
                throw IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.")
            }
        }

        return socialOauth
    }

    fun getRedirectUrl(socialLoginType: String): String {
        val socialOauth = getSocialAuth(socialLoginType)
        val redirectURL: String = socialOauth.getOauthRedirectURL()
        return redirectURL
    }

    fun findSocialUserId(socialLoginType: String, code: String): String? {
        val socialOauth = getSocialAuth(socialLoginType)
        val accessToken = socialOauth.getAccessToken(code) ?: return null
        return socialOauth.getSocialUserId(accessToken)
    }
}