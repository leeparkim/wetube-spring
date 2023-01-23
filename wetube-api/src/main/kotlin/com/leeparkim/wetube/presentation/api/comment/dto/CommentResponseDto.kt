package com.leeparkim.wetube.presentation.api.comment.dto

import com.leeparkim.wetube.domain.comment.Comment
import com.leeparkim.wetube.presentation.api.user.dto.UserResponseDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory

data class CommentResponseDto (
        val commentId: Long,
        val author: UserResponseDto?,
        val likes: Long,
        val content: String,
        val createdAt: String
) {
    companion object {
        fun toDto(comment: Comment): CommentResponseDto {
            return CommentResponseDto(
                    commentId = comment.id,
                    author = UserResponseDto.toDto(comment.user),
                    createdAt = comment.createdAt.toString(),
                    content=comment.content,
                    likes = comment.likes
            )
        }
    }
}
