package com.group.kamiloses.orderstreamapp.kafka;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaProducer {


    private final KafkaTemplate<String, OrderEntity> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, OrderEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<Void> sendMessage(OrderEntity message) {
        return Mono.fromFuture(() -> kafkaTemplate.send("OrderStatusUpdate", message))
                .then()
                .doOnSuccess(success -> log.info("Message has been sent"))
                .doOnError(throwable -> log.error("Error sending message",throwable));
    }



}


