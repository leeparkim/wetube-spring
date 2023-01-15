package com.leeparkim.wetube.presentation.api.post.dto

data class PatchPostRequestDto (
        val title: String,
        val content: String,
        val thumbnailImg: String
)