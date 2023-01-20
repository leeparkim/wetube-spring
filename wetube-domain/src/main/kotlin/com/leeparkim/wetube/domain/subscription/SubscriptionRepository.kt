package com.leeparkim.wetube.domain.subscription

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SubscriptionRepository : JpaRepository<Subscription, Long> {
    fun existsByFollowerIdAndFollowingId(followerId: Long, followingId: Long): Boolean

    @Query(value = "select s from Subscription s join fetch s.following where s.followerId=:followerId",
            countQuery = "select count(s.id) from Subscription s where s.followerId=:followerId")
    fun findByFollowerId(followerId: Long, pageable: Pageable): Page<Subscription>

    @Query(value = "select s from Subscription s join fetch s.follower where s.followingId=:followingId",
            countQuery = "select count(s.id) from Subscription s where s.followingId=:followingId")
    fun findByFollowingId(followingId: Long, pageable: Pageable): Page<Subscription>
}