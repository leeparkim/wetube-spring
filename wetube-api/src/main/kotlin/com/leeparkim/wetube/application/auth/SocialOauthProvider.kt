package com.leeparkim.wetube.application.auth

import com.leeparkim.wetube.domain.user.SocialType
import org.springframework.stereotype.Component

@Component
class SocialOauthProvider(
        val socialOauth: List<SocialOauth>
) {
    val socialOauthMap: HashMap<SocialType, SocialOauth> = HashMap();

    init {
        socialOauth.forEach { social ->
            socialOauthMap[social.getType()] = social
        }
    }

    fun getSocialOauth(socialType: SocialType): SocialOauth {
        return socialOauthMap[socialType] ?: throw IllegalArgumentException("Not exists social")
    }
}