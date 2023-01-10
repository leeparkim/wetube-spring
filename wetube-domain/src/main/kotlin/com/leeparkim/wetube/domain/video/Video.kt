package com.leeparkim.wetube.domain.video

import com.leeparkim.wetube.domain.BaseEntity
import com.leeparkim.wetube.domain.user.User
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class Video(
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,
        val fileUrl: String,
) : BaseEntity()