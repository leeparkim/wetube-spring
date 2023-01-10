package com.leeparkim.wetube.presentation.api.post.dto
import com.leeparkim.wetube.presentation.api.user.dto.UserResDTO

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