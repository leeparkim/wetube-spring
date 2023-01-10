package com.leeparkim.wetube.domain.like

import com.leeparkim.wetube.domain.BaseEntity
import com.leeparkim.wetube.domain.comment.Comment
import com.leeparkim.wetube.domain.user.User
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class CommentLike(
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,
        @ManyToOne(fetch = FetchType.LAZY)
        val comment: Comment,
) : BaseEntity()
