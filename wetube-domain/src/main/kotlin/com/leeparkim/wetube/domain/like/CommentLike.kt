package com.leeparkim.wetube.domain.like

import com.leeparkim.wetube.domain.BaseEntity
import com.leeparkim.wetube.domain.comment.Comment
import com.leeparkim.wetube.domain.user.User
import javax.persistence.*

@Entity
class CommentLike(
        @Column(name = "user_id")
        val userId: Long,

        @Column(name = "comment_id")
        val commentId: Long,

        @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", insertable = false, updatable = false)
        val user: User? = null,

        @ManyToOne(targetEntity = Comment::class, fetch = FetchType.LAZY)
        @JoinColumn(name = "comment_id", insertable = false, updatable = false)
        val comment: Comment? = null,
) : BaseEntity()
