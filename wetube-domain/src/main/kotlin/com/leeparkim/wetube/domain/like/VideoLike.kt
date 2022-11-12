package com.leeparkim.wetube.domain.like

import com.leeparkim.wetube.domain.BaseEntity
import com.leeparkim.wetube.domain.user.User
import com.leeparkim.wetube.domain.video.Video
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class VideoLike(
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,
    @ManyToOne(fetch = FetchType.LAZY)
    val video: Video,
) : BaseEntity()