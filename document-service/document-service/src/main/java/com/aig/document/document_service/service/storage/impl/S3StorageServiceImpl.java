package com.aig.document.document_service.service.storage.impl;
import com.aig.document.document_service.service.storage.S3StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3StorageServiceImpl implements S3StorageService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) {

        try {

            String key =
                    UUID.randomUUID()
                            + "-"
                            + file.getOriginalFilename();

            PutObjectRequest request =
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build();

            s3Client.putObject(
                    request,
                    RequestBody.fromBytes(
                            file.getBytes()));

            return String.format(
                    "https://%s.s3.amazonaws.com/%s",
                    bucketName,
                    key);

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Failed to upload file",
                    ex);
        }
    }
}