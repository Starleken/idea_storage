package ru.betrayal.fileservice.configuration;

import com.amazonaws.auth.*;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.accessSecret}")
    private String accessSecret;
    @Value("${cloud.aws.credentials.region}")
    private String region;

    @Value("${cloud.aws.credentials.endPoint}")
    private String serviceEndPoint;

    @Bean
    public AmazonS3 amazonS3Client() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, accessSecret);
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        serviceEndPoint,
                        region
                ))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    @Bean
    public TransferManager transferManager() {
        return TransferManagerBuilder
                .standard()
                .withS3Client(amazonS3Client())
                .build();
    }
}
