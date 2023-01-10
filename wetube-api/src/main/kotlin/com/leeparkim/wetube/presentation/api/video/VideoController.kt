package com.leeparkim.wetube.presentation.api.video

import com.leeparkim.wetube.infrastructure.file.FileUploader
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/v1/videos")
class VideoController(private val fileUploader: FileUploader) {

    @PostMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun uploadProfileImage(@RequestParam("video") multipartFile: MultipartFile?): String? {
        return fileUploader.upload(multipartFile)
    }
}