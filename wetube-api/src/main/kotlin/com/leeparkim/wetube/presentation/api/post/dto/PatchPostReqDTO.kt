package com.leeparkim.wetube.presentation.api.post.dto

data class PatchPostReqDTO (
        val title: String,
        val content: String,
        val thumbnailImg: String
)