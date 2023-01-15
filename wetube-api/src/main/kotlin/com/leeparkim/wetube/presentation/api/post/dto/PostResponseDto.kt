package com.leeparkim.wetube.presentation.api.post.dto

import com.leeparkim.wetube.presentation.api.user.dto.UserResponseDto

data class PostResponseDto(
        val postId: Long,
        val author: UserResponseDto,
        val title: String,
        val createdAt: String,
        val thumbnailImg: String,
        val view: Long,
        val videoLength: Long
)