package com.leeparkim.wetube.infrastructure.file

import org.springframework.web.multipart.MultipartFile
import java.io.IOException

interface FileUploader {
    @Throws(IOException::class)
    fun upload(multipartFile: MultipartFile?): String?
}