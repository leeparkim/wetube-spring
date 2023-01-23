package com.leeparkim.wetube.domain.like

import org.springframework.stereotype.Service

@Service
class LikeService(private val commentLikeRepository: CommentLikeRepository,
                  private val videoLikeRepository: VideoLikeRepository) {

    fun saveVideoLike(videoLike: VideoLike) {

        videoLikeRepository.save(videoLike)
    }

    fun saveCommentLike(commentLike: CommentLike) {
        commentLikeRepository.save(commentLike)
    }

    fun deleteVideoLike(videoLikeId: Long) {
        videoLikeRepository.deleteById(videoLikeId)
    }

    fun deleteCommentLike(commentLikeId: Long) {
        commentLikeRepository.deleteById(commentLikeId)
    }

    fun getCommentLike(userId: Long, commentId: Long): CommentLike? {
        return commentLikeRepository.findByUserIdAnAndCommentIdOrNull(userId, commentId)
    }

    fun getVideoLike(userId: Long, videoId: Long): VideoLike? {
        return videoLikeRepository.findByUserIdAnAndVideoIdOrNull(userId, videoId)
    }

    fun getVideoLikeCount(videoId: Long): Long {
        return videoLikeRepository.countByVideoId(videoId)
    }

    fun getCommentLikeCount(commentId: Long): Long {
        return commentLikeRepository.countByCommentId(commentId)
    }
}