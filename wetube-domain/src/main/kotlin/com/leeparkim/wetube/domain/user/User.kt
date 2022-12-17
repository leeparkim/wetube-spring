package com.leeparkim.wetube.domain.user

import com.leeparkim.wetube.domain.BaseEntity
import javax.persistence.*

@Entity
@Table(name="users", indexes = [Index(columnList = "socialId, socialType", unique = true)])
class User(
        val profileUrl: String? = null,
        val username: String,
        val socialId: String,
        @Enumerated(EnumType.STRING) val socialType: SocialType,
) : BaseEntity()
