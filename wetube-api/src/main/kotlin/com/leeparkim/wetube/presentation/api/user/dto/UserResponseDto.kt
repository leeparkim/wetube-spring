package com.leeparkim.wetube.presentation.api.user.dto

data class UserResponseDto (
        val userId: Long,
        val username: String,
        val profileUrl: String
)