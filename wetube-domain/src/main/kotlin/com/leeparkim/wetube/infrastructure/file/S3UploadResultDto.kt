package com.leeparkim.wetube.infrastructure.file

data class S3UploadResultDto(
    val name: String,
    val url: String,
    val size: Long
)