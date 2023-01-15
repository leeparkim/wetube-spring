package com.leeparkim.wetube.domain.video

import com.leeparkim.wetube.domain.BaseEntity
import com.leeparkim.wetube.domain.post.Post

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToOne

@Entity
class Video(
        val videoLength: Long,
        val fileUrl: String,
        @OneToOne(mappedBy = "video")
        val post: Post,
        @Enumerated(EnumType.STRING) val videoStatus: VideoStatus
) : BaseEntity()