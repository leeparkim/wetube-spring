package com.leeparkim.wetube.domain.comment

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CommentService(private val commentRepository: CommentRepository) {
    fun createComment(comment: Comment) {
        commentRepository.save(comment)
    }

    fun deleteComment(commentId: Long) {
        commentRepository.deleteById(commentId)
    }

    fun updateComment(commentId: Long, content: String) {
        val comment = commentRepository.findById(commentId).get()
        comment.content = content
    }

    fun addLikeComment(commentId: Long) {
        val comment = commentRepository.findByIdForUpdate(commentId)
        comment.likes = comment.likes + 1
    }

    fun subLikeComment(commentId: Long) {
        val comment = commentRepository.findByIdForUpdate(commentId)
        comment.likes = comment.likes - 1
    }

    @Transactional(readOnly = true)
    fun getUserComment(userId: Long, pageable: Pageable): Page<Comment> {
        return commentRepository.findByUserId(userId, pageable)
    }

    @Transactional(readOnly = true)
    fun getPostComment(postId: Long, pageable: Pageable): Page<Comment> {
        return commentRepository.findByPostId(postId, pageable)
    }
}