package com.leeparkim.wetube.domain.user

import com.leeparkim.wetube.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class User(
        val profileUrl: String?,
        val username: String,
        val socialId: String,
        @Enumerated(EnumType.STRING) val socialType: SocialType,
) : BaseEntity()
