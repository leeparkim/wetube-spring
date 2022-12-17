package com.leeparkim.wetube.domain.exception

import com.leeparkim.wetube.domain.ResultCode

/**
 * 내부 로직에 의해 발생하는 예외
 * API 서버에서 http status code 500 으로 응답함
 */
open class BusinessException(
        open val resultCode: ResultCode,
        override val message: String? = null,
        override val cause: Throwable? = null,
) : RuntimeException(message, cause)