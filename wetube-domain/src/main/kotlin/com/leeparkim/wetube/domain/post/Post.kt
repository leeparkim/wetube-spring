package com.leeparkim.wetube.domain.post

import com.leeparkim.wetube.domain.BaseEntity
import com.leeparkim.wetube.domain.user.User
import com.leeparkim.wetube.domain.video.Video
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class Post(
        @OneToOne(fetch = FetchType.LAZY)
        val video: Video,
        val content: String,
        val title: String,
        val view: Long,
        val thumbnailImg: String,
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,
) : BaseEntity()