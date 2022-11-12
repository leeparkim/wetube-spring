package com.leeparkim.wetube.domain.subscription

import com.leeparkim.wetube.domain.BaseEntity
import com.leeparkim.wetube.domain.user.User
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class Subscription(
    @ManyToOne(fetch = FetchType.LAZY)
    val follower: User,
    @ManyToOne(fetch = FetchType.LAZY)
    val following: User,
) : BaseEntity()
