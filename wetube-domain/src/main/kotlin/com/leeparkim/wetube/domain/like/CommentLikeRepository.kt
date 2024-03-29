package com.leeparkim.wetube.domain.like

import org.springframework.data.jpa.repository.JpaRepository

interface CommentLikeRepository : JpaRepository<CommentLike, Long> {
    fun countByCommentId(commentId: Long): Long
    fun findByUserIdAndCommentId(userId: Long, commentId: Long): CommentLike?
}