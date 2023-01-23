package com.leeparkim.wetube.application.subscription

import com.leeparkim.wetube.domain.subscription.Subscription
import com.leeparkim.wetube.domain.subscription.SubscriptionService
import com.leeparkim.wetube.presentation.api.subscription.dto.SubscriptionResponseDto
import com.leeparkim.wetube.presentation.api.user.dto.UserResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SubscriptionApplicationService(private val subscriptionService: SubscriptionService) {
    fun createSubscription(followerId: Long, followingId: Long) {
        val subscription = Subscription(followerId = followerId, followingId = followingId)
        subscriptionService.createSubscription(subscription)
    }

    fun getFollowers(userId: Long, pageable: Pageable): Page<SubscriptionResponseDto> {
        return subscriptionService.getFollowers(userId, pageable).map {
            SubscriptionResponseDto(
                    user = UserResponseDto.toDto(it.follower),
                    followerId = it.followerId,
                    followingId = it.followingId
            )
        }
    }

    fun getFollowings(userId: Long, pageable: Pageable): Page<SubscriptionResponseDto> {
        return subscriptionService.getFollowings(userId, pageable).map {
            SubscriptionResponseDto(
                    user = UserResponseDto.toDto(it.following),
                    followerId = it.followerId,
                    followingId = it.followingId
            )
        }
    }
}