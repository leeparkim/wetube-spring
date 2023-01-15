package com.leeparkim.wetube.infrastructure.file

data class S3UploadSignedUrlDto(val fileName: String, val uploadId: String, val partNumber: Long)