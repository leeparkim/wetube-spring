package com.leeparkim.wetube.presentation.api.post.dto

import com.leeparkim.wetube.presentation.api.user.dto.UserResDTO

data class PostResDTO(
        val postId: Long,
        val author: UserResDTO,
        val title: String,
        val createdAt: String,
        val thumbnailImg: String,
        val view: Long,
        val videoLength: Long
)