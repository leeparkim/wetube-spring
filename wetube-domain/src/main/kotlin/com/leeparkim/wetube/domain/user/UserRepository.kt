package com.leeparkim.wetube.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findBySocialIdAndSocialType(socialId: String, socialType: SocialType): User?
}