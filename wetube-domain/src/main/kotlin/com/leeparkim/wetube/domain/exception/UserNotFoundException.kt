package com.leeparkim.wetube.domain.exception

import com.leeparkim.wetube.domain.ResultCode

class UserNotFoundException(
        override val message: String?,
        override val cause: Throwable? = null
):NotFoundException(
        resultCode = ResultCode.USER_NOT_FOUND,
        message = message,
        cause = cause
)