package com.leeparkim.wetube.infrastructure.file

data class S3UploadPartsDetailDto(
    val partNumber: Int,
    val awsETag: String
)