package com.leeparkim.wetube.presentation.api.video

import com.leeparkim.wetube.infrastructure.file.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Video", description = "비디오 관련 API")
@RestController
@RequestMapping("api/v1/videos")
class VideoController(private val fileUploader: FileUploader) {

    @Operation(summary = "비디오 업로드 시작 API")
    @PostMapping("/initiate-upload")
    fun initiateUpload(@RequestBody body: S3UploadInitiateDto): S3UploadDto {
        return fileUploader.initiateUpload(body.fileName)
    }

    @Operation(summary = "비디오 presigned url 요청 API")
    @PostMapping("/upload-signed-url")
    fun getUploadSignedUrl(@RequestBody body: S3UploadSignedUrlDto): S3PresignedUrlDto {
        return fileUploader.getUploadSignedUrl(body)
    }

    @Operation(summary = "비디오 업로드 완료 호출 API")
    @PostMapping("/complete-upload")
    fun completeUpload(@RequestBody body: S3UploadCompleteDto): S3UploadResultDto {
        return fileUploader.completeUpload(body)
    }

    @Operation(summary = "비디오 업로드 중지 API")
    @PostMapping("/abort-upload")
    fun abortUpload(@RequestBody body: S3UploadAbortDto) {
        fileUploader.abortUpload(body)
    }
}