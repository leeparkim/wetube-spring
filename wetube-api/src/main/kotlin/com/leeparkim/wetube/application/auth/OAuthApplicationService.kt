package com.leeparkim.wetube.application.auth

import com.leeparkim.wetube.domain.user.SocialType
import com.leeparkim.wetube.domain.user.User
import com.leeparkim.wetube.domain.user.UserRepository
import com.leeparkim.wetube.presentation.auth.dto.SignInResponseDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.inject.Provider


@Service
class OAuthApplicationService(
        val socialOauthProvider: SocialOauthProvider,
        val userRepository: UserRepository,
        val tokenService: TokenService<Long>) {

    fun getSocialAuth(socialLoginType: String): SocialOauth {
        return socialOauthProvider.getSocialOauth(SocialType.GOOGLE)
    }

    fun getRedirectUrl(socialLoginType: String): String {
        val socialOauth = getSocialAuth(socialLoginType)
        return socialOauth.getOauthRedirectURL()
    }

    fun getSocialToken(socialLoginType: String, code: String): String? {
        val socialOauth = getSocialAuth(socialLoginType)
        return socialOauth.getAccessToken(code)
    }

    fun getSocialUserIdAndEmail(socialLoginType: String, accessToken: String): Pair<String, String>? {
        val socialOauth = getSocialAuth(socialLoginType)
        return socialOauth.getSocialUserIdAndEmail(accessToken)
    }

    fun findUser(socialId: String, socialLoginType: String): User? {
        val socialType = SocialType.valueOf(socialLoginType.uppercase())
        return userRepository.findBySocialIdAndSocialType(socialId, socialType)
    }

    fun getTokenByUser(user: User): SignInResponseDTO {
        val accessToken = tokenService.encode(user.id)
        return SignInResponseDTO(accessToken)
    }

    fun createUser(socialUserId: String, socialLoginType: String, email: String): User {
        val socialType: SocialType = SocialType.valueOf(socialLoginType.uppercase())
        val username = email.split("@")[0]

        return userRepository.save(User(
                socialId = socialUserId,
                socialType = socialType,
                username = username
        ))
    }
}