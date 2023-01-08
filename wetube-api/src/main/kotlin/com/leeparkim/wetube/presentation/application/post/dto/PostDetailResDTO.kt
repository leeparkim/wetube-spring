package com.leeparkim.wetube.presentation.application.post.dto
import com.leeparkim.wetube.presentation.application.user.dto.UserResDTO

data class PostDetailResDTO(
        val postId: Long,
        val author: UserResDTO,
        val title: String,
        val content: String,
        val createdAt: String,
        val thumbnailImg: String,
        val view: Long,
        val videoLength: Long,
        val liked: Boolean
)