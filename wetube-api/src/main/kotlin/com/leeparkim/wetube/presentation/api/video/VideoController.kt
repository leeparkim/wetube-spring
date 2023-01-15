package com.leeparkim.wetube.presentation.api.video

import com.leeparkim.wetube.infrastructure.file.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/videos")
class VideoController(private val fileUploader: FileUploader) {

    @PostMapping("/initiate-upload")
    fun initiateUpload(@RequestBody body: S3UploadInitiateDto): S3UploadDto {
        return fileUploader.initiateUpload(body.fileName)
    }

    @PostMapping("/upload-signed-url")
    fun getUploadSignedUrl(@RequestBody body: S3UploadSignedUrlDto): S3PresignedUrlDto {
        return fileUploader.getUploadSignedUrl(body)
    }

    @PostMapping("/complete-upload")
    fun completeUpload(@RequestBody body: S3UploadCompleteDto): S3UploadResultDto {
        return fileUploader.completeUpload(body)
    }

    @PostMapping("/abort-upload")
    fun abortUpload(@RequestBody body: S3UploadAbortDto) {
        fileUploader.abortUpload(body)
    }
}