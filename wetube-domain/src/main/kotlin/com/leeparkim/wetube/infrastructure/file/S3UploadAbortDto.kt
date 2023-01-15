package com.leeparkim.wetube.infrastructure.file

data class S3UploadAbortDto(
    val fileName: String,
    val uploadId: String
)