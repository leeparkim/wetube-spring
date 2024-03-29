package com.leeparkim.wetube.domain.like

import org.springframework.data.jpa.repository.JpaRepository

interface VideoLikeRepository: JpaRepository<VideoLike, Long> {
    fun countByVideoId(videoId: Long): Long
    fun findByUserIdAndVideoId(userId: Long, videoId: Long): VideoLike?
}