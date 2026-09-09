//package com.aig.ocr_service.consumer;
//
//import com.aig.common.dto.DocumentCreatedEvent;
//import com.aig.ocr_service.service.Ocrservice;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class OcrConsumer {
//
//    //this is for dummy
//    private final Ocrservice ocrservice;
//    @KafkaListener(
//            topics = "document-created-topic",
//            groupId = "ocr-group")
//    public void consume(DocumentCreatedEvent event) {
//
//        //for dummy
//        log.info(
//                "Dummy ocr Service Received Event : {}",
//                event);
//        ocrservice.processDocument(event);
//    }
//}