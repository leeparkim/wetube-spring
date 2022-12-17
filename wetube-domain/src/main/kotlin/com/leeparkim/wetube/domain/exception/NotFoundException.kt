package com.leeparkim.wetube.domain.exception

/**
 * 조회 실패시 발생하는 예외
 * api 서버에서 http status code 404 로 응답함
 */
class NotFoundException(
        override val message: String?,
        override val cause: Throwable? = null,
) : Exception(
        message = message,
        cause = cause
)
