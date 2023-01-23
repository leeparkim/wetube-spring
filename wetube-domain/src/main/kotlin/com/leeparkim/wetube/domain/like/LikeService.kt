package com.leeparkim.wetube.domain.like

import com.leeparkim.wetube.domain.comment.CommentService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class LikeService(private val commentLikeRepository: CommentLikeRepository,
                  private val videoLikeRepository: VideoLikeRepository,
                  private val commentService: CommentService) {

    fun saveVideoLike(videoLike: VideoLike) {
        videoLikeRepository.save(videoLike)
    }

    fun saveCommentLike(commentLike: CommentLike) {
        commentService.addLikeComment(commentLike.commentId)
        commentLikeRepository.save(commentLike)
    }

    fun deleteVideoLike(videoLike: VideoLike) {
        videoLikeRepository.deleteById(videoLike.id)
    }

    fun deleteCommentLike(commentLike: CommentLike) {
        commentService.subLikeComment(commentLike.commentId)
        commentLikeRepository.deleteById(commentLike.id)
    }

    @Transactional(readOnly = true)
    fun getCommentLike(userId: Long, commentId: Long): CommentLike? {
        return commentLikeRepository.findByUserIdAndCommentId(userId, commentId)
    }

    @Transactional(readOnly = true)
    fun getVideoLike(userId: Long, videoId: Long): VideoLike? {
        return videoLikeRepository.findByUserIdAndVideoId(userId, videoId)
    }

    @Transactional(readOnly = true)
    fun getVideoLikeCount(videoId: Long): Long {
        return videoLikeRepository.countByVideoId(videoId)
    }

    @Transactional(readOnly = true)
    fun getCommentLikeCount(commentId: Long): Long {
        return commentLikeRepository.countByCommentId(commentId)
    }
}