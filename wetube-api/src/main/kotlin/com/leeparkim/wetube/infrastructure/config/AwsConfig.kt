package com.leeparkim.wetube.infrastructure.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.presigner.S3Presigner

@Configuration
class AwsConfig {
    @Value("\${cloud.aws.credentials.accessKey}")
    private val accessKey: String? = null

    @Value("\${cloud.aws.credentials.secretKey}")
    private val secretKey: String? = null

    @Value("\${cloud.aws.region.static}")
    private val region: String? = null

    @Bean
    fun awsCredentials(): AwsCredentials {
        return AwsBasicCredentials.create(accessKey, secretKey)
    }

    @Bean
    fun amazonS3Client(awsCredentials: AwsCredentials): AmazonS3 {
        val basicAWSCredentials = BasicAWSCredentials(accessKey, secretKey)
        return AmazonS3Client.builder()
            .withCredentials(AWSStaticCredentialsProvider(basicAWSCredentials))
            .withRegion(region)
            .build()
    }

    @Bean
    fun s3Client(awsCredentials: AwsCredentials): S3Client {
        return S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
            .build()
    }

    @Bean
    fun s3Presigner(awsCredentials: AwsCredentials): S3Presigner {
        return S3Presigner.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
            .build()
    }
}
