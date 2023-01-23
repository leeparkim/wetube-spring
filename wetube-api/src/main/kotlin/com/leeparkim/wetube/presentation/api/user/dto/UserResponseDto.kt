package com.leeparkim.wetube.presentation.api.user.dto

import com.leeparkim.wetube.domain.user.User

data class UserResponseDto(
        val id: Long,
        val username: String,
        val profileUrl: String?
) {
    companion object {
        fun toDto(user: User?): UserResponseDto? {
            if (user != null) {
                return UserResponseDto(
                        id = user.id,
                        username = user.username,
                        profileUrl = user.profileUrl
                )
            }
            return null
        }
    }
}