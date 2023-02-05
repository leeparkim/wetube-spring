package com.leeparkim.wetube.domain.subscription

import com.leeparkim.wetube.domain.BaseEntity
import com.leeparkim.wetube.domain.user.User
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Subscription(
        @Column(name = "follower_id")
        val followerId: Long,

        @Column(name = "following_id")
        val followingId: Long,

        @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY)
        @JoinColumn(name = "follower_id", insertable = false, updatable = false)
        val follower: User? = null,

        @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY)
        @JoinColumn(name = "following_id", insertable = false, updatable = false)
        val following: User? = null,
) : BaseEntity()
