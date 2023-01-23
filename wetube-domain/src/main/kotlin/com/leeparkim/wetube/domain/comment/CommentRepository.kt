package com.leeparkim.wetube.domain.comment

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.QueryHints
import javax.persistence.LockModeType
import javax.persistence.QueryHint

interface CommentRepository : JpaRepository<Comment, Long> {
    @Query(value = "select c from Comment c join fetch c.user where c.user.id = :userId",
            countQuery = "select count(c.id) from Comment c where c.user.id = :userId")
    fun findByUserId(userId: Long, pageable: Pageable): Page<Comment>

    @Query(value = "select c from Comment c join fetch c.user where c.post.id = :postId",
            countQuery = "select count(c.id) from Comment c where c.post.id = :postId")
    fun findByPostId(postId: Long, pageable: Pageable): Page<Comment>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Comment c where c.id = :id")
    fun findByIdForUpdate(id: Long): Comment
}