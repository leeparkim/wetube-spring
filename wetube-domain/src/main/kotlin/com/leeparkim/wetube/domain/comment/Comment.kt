package com.leeparkim.wetube.domain.comment

import com.leeparkim.wetube.domain.BaseEntity
import com.leeparkim.wetube.domain.user.User
import com.leeparkim.wetube.domain.video.Video
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class Comment(
        @ManyToOne(fetch = FetchType.LAZY)
        val video: Video,
        @ManyToOne
        val user: User,
        val content: String,
) : BaseEntity()
