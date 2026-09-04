//package com.aig.audit.config;
//
//import com.aig.audit.dto.AuditEvent;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableKafka
//public class KafkaConsumerConfig {
//
//    @Bean
//    public ConsumerFactory<String, AuditEvent> consumerFactory() {
//
//        JsonDeserializer<AuditEvent> deserializer =
//                new JsonDeserializer<>(AuditEvent.class);
//
//        deserializer.addTrustedPackages("*");
//
//        Map<String, Object> config = new HashMap<>();
//
//        config.put(
//                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                "localhost:9092");
//
//        config.put(
//                ConsumerConfig.GROUP_ID_CONFIG,
//                "audit-group");
//
//        config.put(
//                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//
//        config.put(
//                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//                JsonDeserializer.class);
//
//        config.put(
//                JsonDeserializer.TRUSTED_PACKAGES,
//                "*");
//
//        return new DefaultKafkaConsumerFactory<>(
//                config,
//                new StringDeserializer(),
//                deserializer
//        );
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, AuditEvent>
//    kafkaListenerContainerFactory() {
//
//        ConcurrentKafkaListenerContainerFactory<String, AuditEvent> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//
//        factory.setConsumerFactory(
//                consumerFactory()
//        );
//
//        return factory;
//    }
//}