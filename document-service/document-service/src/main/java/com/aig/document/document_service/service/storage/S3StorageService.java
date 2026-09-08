package com.aig.document.document_service.service.storage;

import org.springframework.web.multipart.MultipartFile;

public interface S3StorageService {

    String uploadFile(MultipartFile file);
}