package com.leeparkim.wetube.domain.like

import com.leeparkim.wetube.domain.BaseEntity
import com.leeparkim.wetube.domain.comment.Comment
import com.leeparkim.wetube.domain.user.User
import com.leeparkim.wetube.domain.video.Video
import javax.persistence.*

@Entity
class VideoLike(
        @Column(name = "user_id")
        val userId: Long,

        @Column(name = "video_id")
        val videoId: Long,

        @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", insertable = false, updatable = false)
        val user: User? = null,

        @ManyToOne(targetEntity = Video::class, fetch = FetchType.LAZY)
        @JoinColumn(name = "video_id", insertable = false, updatable = false)
        val video: Video? = null
) : BaseEntity()