package com.leeparkim.wetube.presentation.auth

import com.leeparkim.wetube.application.auth.OAuthApplicationService
import com.leeparkim.wetube.presentation.auth.dto.SignInResponseDTO
import com.leeparkim.wetube.presentation.auth.dto.SigninRequestDTO
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
class SocialOauthController(private val oAuthService: OAuthApplicationService) {

    @GetMapping("/signin/social/{socialType}/form")
    fun socialLoginRedirect(@PathVariable(name = "socialType") socialTypeString: String): String {
        val redirectUrl = oAuthService.getRedirectUrl(socialTypeString);

        return "redirect:$redirectUrl"
    }

    @ResponseBody
    @GetMapping("/signin/social/{socialType}/form/callback")
    fun socialCallback(@PathVariable(name = "socialType") socialTypeString: String, code: String): SignInResponseDTO {
        val socialToken = oAuthService.getSocialToken(socialTypeString, code)
                ?: throw IllegalStateException("Invalid response from Google API")
        val (socialUserId, email) = oAuthService.getSocialUserIdAndEmail(socialTypeString, socialToken)
                ?: throw IllegalStateException("User not found")

        val user = oAuthService.findUser(socialUserId, socialTypeString)
                ?: oAuthService.createUser(socialUserId, socialTypeString, email)

        return oAuthService.getTokenByUser(user)
    }

    @ResponseBody
    @PostMapping("/api/signin/social/{socialType}")
    fun socialLogin(@PathVariable(name = "socialType") socialTypeString: String,
                    @RequestBody signinRequest: SigninRequestDTO): SignInResponseDTO {

        val (socialUserId, email) = oAuthService.getSocialUserIdAndEmail(socialTypeString, signinRequest.accessToken)
                ?: throw IllegalStateException("User not found")

        if (socialUserId != signinRequest.userId) {
            throw IllegalStateException("Invalid request")
        }

        val user = oAuthService.findUser(socialUserId, socialTypeString)
                ?: oAuthService.createUser(socialUserId, socialTypeString, email)

        return oAuthService.getTokenByUser(user)
    }
}