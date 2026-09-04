package com.aig.document.document_service;

import com.aig.document.document_service.event.DocumentCreatedEvent;
import com.aig.document.document_service.producer.DocumentEventProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class DocumentServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(DocumentServiceApplication.class, args);

	}
}
