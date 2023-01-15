package com.leeparkim.wetube.infrastructure.file

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GetObjectMetadataRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.*
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.UploadPartPresignRequest
import java.time.Duration
import java.time.Instant

@Service
class S3Uploader(
    private val s3Client: S3Client,
    private val amazonS3: AmazonS3,
    private val s3Presigner: S3Presigner
) : FileUploader {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    @Value("\${cloud.aws.s3.dir}")
    private val dir: String? = null

    override fun initiateUpload(originFileName: String): S3UploadDto {
        val fileType = originFileName.substringAfterLast(".").lowercase()
        val newFileName = "${System.currentTimeMillis()}.$fileType"
        val now = Instant.now()

        val createMultipartUploadRequest = CreateMultipartUploadRequest.builder()
            .bucket(bucket)
            .key("$dir/$newFileName")
            .acl(ObjectCannedACL.PUBLIC_READ)
            .expires(now.plusSeconds(60 * 20))
            .build()

        val response = s3Client.createMultipartUpload(createMultipartUploadRequest)

        return S3UploadDto(response.uploadId(), newFileName)
    }

    override fun getUploadSignedUrl(s3UploadSignedUrlDto: S3UploadSignedUrlDto): S3PresignedUrlDto {
        val request = UploadPartRequest.builder()
            .bucket(bucket)
            .key(dir + "/" + s3UploadSignedUrlDto.fileName)
            .uploadId(s3UploadSignedUrlDto.uploadId)
            .partNumber(s3UploadSignedUrlDto.partNumber.toInt())
            .build()

        val presignedRequest = UploadPartPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(10))
            .uploadPartRequest(request)
            .build()

        val presingedUploadRequest = s3Presigner.presignUploadPart(presignedRequest)

        return S3PresignedUrlDto(presingedUploadRequest.url().toString())
    }

    override fun completeUpload(s3UploadCompleteDto: S3UploadCompleteDto): S3UploadResultDto {
        val completedParts = s3UploadCompleteDto.parts.map { partForm ->
            CompletedPart.builder()
                .partNumber(partForm.partNumber)
                .eTag(partForm.awsETag)
                .build()
        }

        val completedMultipartUpload = CompletedMultipartUpload.builder()
            .parts(completedParts)
            .build()

        val fileName = s3UploadCompleteDto.fileName
        val completeMultipartUploadRequest = CompleteMultipartUploadRequest.builder()
            .bucket(bucket)
            .key("$dir/$fileName")
            .uploadId(s3UploadCompleteDto.uploadId)
            .multipartUpload(completedMultipartUpload)
            .build()

        val completeMultipartUploadResponse = s3Client.completeMultipartUpload(completeMultipartUploadRequest)

        val objectKey = completeMultipartUploadResponse.key()
        val url = amazonS3.getUrl(bucket, objectKey).toString()
        val bucket = completeMultipartUploadResponse.bucket()

        val fileSize = getFileSizeFromS3Url(bucket, objectKey)

        return S3UploadResultDto(
            name = fileName,
            url = url,
            size = fileSize
        )
    }

    override fun abortUpload(s3UploadAbortDto: S3UploadAbortDto) {
        val request = AbortMultipartUploadRequest.builder()
            .bucket(bucket)
            .key("$dir/${s3UploadAbortDto.fileName}")
            .uploadId(s3UploadAbortDto.uploadId)
            .build()
        s3Client.abortMultipartUpload(request)
    }

    private fun getFileSizeFromS3Url(bucketName: String, fileName: String): Long {
        val metadataRequest = GetObjectMetadataRequest(bucketName, fileName)
        val objectMetadata: ObjectMetadata = amazonS3.getObjectMetadata(metadataRequest)
        return objectMetadata.contentLength
    }
}