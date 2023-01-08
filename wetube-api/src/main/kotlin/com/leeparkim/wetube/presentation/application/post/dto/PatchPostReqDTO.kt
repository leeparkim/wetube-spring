package com.leeparkim.wetube.presentation.application.post.dto

data class PatchPostReqDTO (
        val title: String,
        val content: String,
        val thumbnailImg: String
)