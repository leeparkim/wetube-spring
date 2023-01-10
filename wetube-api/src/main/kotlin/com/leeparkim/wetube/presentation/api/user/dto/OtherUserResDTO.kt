package com.leeparkim.wetube.presentation.api.user.dto

data class OtherUserResDTO (
        val userId: Long,
        val username: String,
        val profileUrl: String,
        val subscripted: Boolean
)