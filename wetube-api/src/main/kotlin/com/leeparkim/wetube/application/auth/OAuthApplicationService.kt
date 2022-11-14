package com.leeparkim.wetube.application.auth

import com.leeparkim.wetube.domain.user.SocialType
import org.springframework.stereotype.Service


@Service
class OAuthApplicationService(var googleOauth: GoogleOauth) {

    fun getSocialAuth(socialLoginType: SocialType?): SocialOauth {
        val socialOauth: SocialOauth = when (socialLoginType) {
            SocialType.GOOGLE -> GoogleOauth()
            else -> {
                throw IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.")
            }
        }

        return socialOauth
    }

    fun getRedirectUrl(socialLoginType: SocialType?): String {
        val socialOauth = getSocialAuth(socialLoginType);
        val redirectURL: String = socialOauth.getOauthRedirectURL();
        return redirectURL;
    }
}