package com.leeparkim.wetube.domain

enum class ResultCode(val message: String = "") {
    SUCCESS("성공"),

    // authorization
    UNAUTHORIZED("인증 실패"),
    FORBIDDEN("권한 없음"),

    // 기타
    BAD_REQUEST("요청에 오류가 있습니다"),
    INTERNAL_SERVER_ERROR("서버 에러"),

    // User
    USER_NOT_FOUND("유저 조회 실패"),
}