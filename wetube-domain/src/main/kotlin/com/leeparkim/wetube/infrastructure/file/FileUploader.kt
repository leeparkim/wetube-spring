package com.leeparkim.wetube.infrastructure.file

interface FileUploader {
    fun initiateUpload(originFileName: String): S3UploadDto
    fun getUploadSignedUrl(s3UploadSignedUrlDto: S3UploadSignedUrlDto): S3PresignedUrlDto
    fun completeUpload(s3UploadCompleteDto: S3UploadCompleteDto): S3UploadResultDto
    fun abortUpload(s3UploadAbortDto: S3UploadAbortDto)
}