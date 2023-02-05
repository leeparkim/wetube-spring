package com.leeparkim.wetube.domain.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class UserService(private val userRepository: UserRepository) {
    fun getById(userId: Long): User? {
        return userRepository.findByIdOrNull(userId)
    }
}