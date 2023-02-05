package com.leeparkim.wetube.domain.subscription

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SubscriptionService(private val subscriptionRepository: SubscriptionRepository) {
    fun isSubscribedByUserId(followerId: Long, followingId: Long): Boolean {
        return subscriptionRepository.existsByFollowerIdAndFollowingId(followerId, followingId)
    }

    @Transactional
    fun createSubscription(subscription: Subscription) {
        subscriptionRepository.save(subscription)
    }

    fun getFollowers(userId: Long, pageable: Pageable): Page<Subscription> {
        return subscriptionRepository.findByFollowingId(userId, pageable)
    }

    fun getFollowings(userId: Long, pageable: Pageable): Page<Subscription> {
        return subscriptionRepository.findByFollowerId(userId, pageable)
    }
}