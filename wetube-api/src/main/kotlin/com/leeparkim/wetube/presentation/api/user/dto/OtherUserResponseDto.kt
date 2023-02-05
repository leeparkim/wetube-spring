package com.leeparkim.wetube.presentation.api.user.dto

data class OtherUserResponseDto(
        val id: Long,
        val username: String,
        val profileUrl: String?,
        val subscribed: Boolean
)