package com.aig.ocr_service.repository;
import com.aig.ocr_service.enity.OcrDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OcrRepository extends JpaRepository<OcrDocument, Long> {

    Optional<OcrDocument> findByDocumentId(
            Long documentId);
}