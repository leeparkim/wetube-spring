package com.leeparkim.wetube.application.auth

interface SocialOauth {
    fun getOauthRedirectURL(): String
}