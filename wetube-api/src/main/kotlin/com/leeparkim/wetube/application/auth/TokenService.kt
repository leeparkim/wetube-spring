package com.leeparkim.wetube.application.auth

interface TokenService<T> {
    fun encode(id: T): String
    fun decode(token: String?): T?
}