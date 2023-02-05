package com.leeparkim.wetube.domain.comment

import com.leeparkim.wetube.domain.BaseEntity
import com.leeparkim.wetube.domain.post.Post
import com.leeparkim.wetube.domain.user.User
import javax.persistence.*

@Entity
class Comment(
        @Column(name = "user_id")
        val userId: Long,

        @Column(name = "post_id")
        val postId: Long,

        var content: String,

        @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", insertable = false, updatable = false)
        val user: User? = null,

        @ManyToOne(targetEntity = Post::class, fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id", insertable = false, updatable = false)
        val post: Post? = null,

        var likes: Long = 0
) : BaseEntity()
