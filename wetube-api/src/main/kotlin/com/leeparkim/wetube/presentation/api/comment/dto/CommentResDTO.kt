package com.leeparkim.wetube.presentation.api.comment.dto

import com.leeparkim.wetube.presentation.api.user.dto.UserResDTO

data class CommentResDTO (
        val commentId: Long,
        val author: UserResDTO,
        val likes: Long,
        val content: String,
        val createdAt: String
)
