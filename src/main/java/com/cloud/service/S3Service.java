package com.cloud.service;

import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.Duration;

@Service
public class S3Service {

    private final S3Template s3Template;
    private final String bucketName;


    public S3Service(S3Template s3Template, @Value("${aws.s3.bucket}") String bucketName) {
        this.s3Template = s3Template;
        this.bucketName = bucketName;
    }


    public String uploadAndGetPresignedUrl(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();


        s3Template.upload(bucketName, originalFilename, file.getInputStream(), null);


        return s3Template.createSignedGetURL(bucketName, originalFilename, Duration.ofMinutes(10)).toString();
    }
}