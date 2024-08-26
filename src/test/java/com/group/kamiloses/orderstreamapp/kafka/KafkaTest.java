package com.group.kamiloses.orderstreamapp.kafka;

import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
import com.group.kamiloses.orderstreamapp.other.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Date;


@SpringBootTest
public class KafkaTest {

    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
     private KafkaConsumer kafkaConsumer;

    private static KafkaContainer kafkaContainer;

    @BeforeAll
    static void setUp(){
        kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest").asCompatibleSubstituteFor("apache/kafka"));
        kafkaContainer.start();
        System.setProperty("spring.kafka.bootstrap-servers", kafkaContainer.getBootstrapServers());
    }

    @Test
    public void testKafkaIntegration() throws Exception {
        OrderEntity orderEntity = new OrderEntity("1", "1", null, new Date(), null, Status.ACCEPTED);
        OrderEntity orderEntity2 = new OrderEntity("2", "1", null, new Date(), null, Status.ACCEPTED);
        kafkaProducer.sendMessage(orderEntity).subscribe();

        Thread.sleep(2000);

        Assertions.assertEquals(orderEntity.toString(),kafkaConsumer.getRecentlyConsumed());
        Assertions.assertNotEquals(orderEntity2.toString(), kafkaConsumer.getRecentlyConsumed());
    }
}
