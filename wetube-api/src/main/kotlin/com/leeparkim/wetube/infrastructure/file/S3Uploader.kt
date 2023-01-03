package com.leeparkim.wetube.infrastructure.file

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class S3Uploader(private val amazonS3: AmazonS3): FileUploader {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    @Value("\${cloud.aws.s3.dir}")
    private val dir: String? = null

    override fun upload(multipartFile: MultipartFile?): String? {
        multipartFile ?: throw IllegalArgumentException("multipartFile must not be null in upload")
        val s3FileName = UUID.randomUUID().toString() + "-" + multipartFile.originalFilename
        val objMeta = ObjectMetadata()
        objMeta.contentLength = multipartFile.inputStream.available().toLong()
        amazonS3.putObject(bucket, s3FileName, multipartFile.inputStream, objMeta)
        return amazonS3.getUrl(bucket, dir + s3FileName).toString()
    }
}