package com.leeparkim.wetube.presentation.api.user.dto

data class UserResponseDto(
        val id: Long,
        val username: String,
        val profileUrl: String?
)