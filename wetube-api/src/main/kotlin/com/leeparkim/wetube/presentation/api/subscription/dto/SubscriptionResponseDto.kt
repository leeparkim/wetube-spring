package com.leeparkim.wetube.presentation.api.subscription.dto

import com.leeparkim.wetube.presentation.api.user.dto.UserResponseDto

data class SubscriptionResponseDto(
        val user: UserResponseDto?,
        val followerId: Long,
        val followingId: Long
)