package com.leeparkim.wetube.presentation.api.comment.dto

import com.leeparkim.wetube.presentation.api.user.dto.UserResponseDto

data class CommentResponseDto (
        val commentId: Long,
        val author: UserResponseDto,
        val likes: Long,
        val content: String,
        val createdAt: String
)
