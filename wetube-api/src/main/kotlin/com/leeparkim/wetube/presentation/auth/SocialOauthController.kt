package com.leeparkim.wetube.presentation.auth

import com.leeparkim.wetube.application.auth.OAuthApplicationService
import com.leeparkim.wetube.domain.user.SocialType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class SocialOauthController(private val oAuthService: OAuthApplicationService) {

    @GetMapping("/signin/social/{socialType}/form")
    fun socialLoginRedirect(@PathVariable(name = "socialType") socialTypeString: String): String {
        val redirectUrl = oAuthService.getRedirectUrl(socialTypeString);

        return "redirect:$redirectUrl"
    }

    @ResponseBody
    @GetMapping("/signin/social/{socialType}/form/callback")
    fun socialCallback(@PathVariable(name = "socialType") socialTypeString: String, code: String): String {
        return oAuthService.findSocialUserId(socialTypeString, code) ?: throw IllegalStateException("User not found")
    }
}