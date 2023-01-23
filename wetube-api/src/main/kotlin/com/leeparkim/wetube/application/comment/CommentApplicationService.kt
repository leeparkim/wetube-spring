package com.leeparkim.wetube.application.comment

import com.leeparkim.wetube.domain.comment.Comment
import com.leeparkim.wetube.domain.comment.CommentService
import com.leeparkim.wetube.presentation.api.comment.dto.CommentResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CommentApplicationService(private val commentService: CommentService) {
    fun createComment(userId: Long, postId: Long, content: String) {
        commentService.createComment(Comment(userId, postId, content))
    }

    fun deleteComment(commentId: Long) {
        commentService.deleteComment(commentId)
    }

    fun updateComment(commentId: Long, content: String) {
        commentService.updateComment(commentId, content)
    }

    fun getUserComment(userId: Long, pageable: Pageable): Page<CommentResponseDto> {
        return commentService.getUserComment(userId, pageable).map {
            CommentResponseDto.toDto(comment = it)
        }
    }

    fun getPostComment(postId: Long, pageable: Pageable): Page<CommentResponseDto> {
        return commentService.getPostComment(postId, pageable).map {
            CommentResponseDto.toDto(comment = it)
        }
    }
}