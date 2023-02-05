package com.leeparkim.wetube.application.user

import com.leeparkim.wetube.domain.exception.UserNotFoundException
import com.leeparkim.wetube.domain.subscription.SubscriptionService
import com.leeparkim.wetube.domain.user.UserService
import com.leeparkim.wetube.presentation.api.user.dto.OtherUserResponseDto
import com.leeparkim.wetube.presentation.api.user.dto.UserResponseDto
import org.springframework.stereotype.Service

@Service
class UserApplicationService(private val userService: UserService,
                             private val subscriptionService: SubscriptionService) {
    fun getMe(userId: Long): UserResponseDto {
        val user = userService.getById(userId) ?: throw UserNotFoundException("해당 유저를 찾을 수 없습니다.")

        return UserResponseDto(
                id = user.id,
                username = user.username,
                profileUrl = user.profileUrl
        )
    }

    fun getOtherUser(targetUserId: Long, userId: Long): OtherUserResponseDto {
        val user = userService.getById(userId) ?: throw UserNotFoundException("해당 유저를 찾을 수 없습니다.")
        val isSubscribed = subscriptionService.isSubscribedByUserId(targetUserId, userId)
        return OtherUserResponseDto(
                id = user.id,
                username = user.username,
                profileUrl = user.profileUrl,
                subscribed = isSubscribed
        )
    }
}