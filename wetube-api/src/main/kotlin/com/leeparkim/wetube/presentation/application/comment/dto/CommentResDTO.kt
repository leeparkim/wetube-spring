package com.leeparkim.wetube.presentation.application.comment.dto

import com.leeparkim.wetube.presentation.application.user.dto.UserResDTO

data class CommentResDTO (
        val commentId: Long,
        val author: UserResDTO,
        val likes: Long,
        val content: String,
        val createdAt: String
)
