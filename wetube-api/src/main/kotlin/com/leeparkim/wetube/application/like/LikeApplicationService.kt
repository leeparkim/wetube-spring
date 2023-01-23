package com.leeparkim.wetube.application.like

import com.leeparkim.wetube.domain.like.CommentLike
import com.leeparkim.wetube.domain.like.LikeService
import com.leeparkim.wetube.domain.like.VideoLike
import org.springframework.stereotype.Service

@Service
class LikeApplicationService(private val likeService: LikeService) {
    fun clickVideoLike(userId: Long, videoId: Long) {
        val videoLike = likeService.getVideoLike(userId, videoId)
        if (videoLike != null)
            likeService.deleteVideoLike(videoLike)
        else
            likeService.saveVideoLike(VideoLike(userId, videoId))
    }

    fun clickCommentLike(userId: Long, commentId: Long) {
        val commentLike = likeService.getCommentLike(userId, commentId)
        if (commentLike != null)
            likeService.deleteCommentLike(commentLike)
        else
            likeService.saveCommentLike(CommentLike(userId, commentId))
    }
}