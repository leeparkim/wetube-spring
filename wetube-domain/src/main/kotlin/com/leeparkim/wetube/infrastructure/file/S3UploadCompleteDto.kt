package com.leeparkim.wetube.infrastructure.file

data class S3UploadCompleteDto(
    val uploadId: String,
    val fileName: String,
    val parts: List<S3UploadPartsDetailDto>
)