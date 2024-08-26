package com.group.kamiloses.orderstreamapp.kafka;

import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
import com.group.kamiloses.orderstreamapp.service.EmailSenderService;
import com.group.kamiloses.orderstreamapp.other.Status;
import com.group.kamiloses.orderstreamapp.repository.OrderRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class KafkaConsumer {

    private final OrderRepository orderRepository;
    private final EmailSenderService emailSenderService;
    private OrderEntity recentlyConsumed;

    public KafkaConsumer(OrderRepository orderRepository, EmailSenderService emailSenderService) {
        this.orderRepository = orderRepository;
        this.emailSenderService = emailSenderService;
    }

    @KafkaListener(topics = "OrderStatusUpdate", groupId = "order-status-group")

    public void consumeMessage(OrderEntity orderEntity) {
        consumeOrderEntity(orderEntity).subscribe();
        this.recentlyConsumed = orderEntity;
    }


    public Mono<OrderEntity> consumeOrderEntity(OrderEntity order) {
        return orderRepository.findById(order.getId()).flatMap(orderEntity -> {
            log.info("Original status: {}", orderEntity.getStatus());

            if (orderEntity.getStatus().equals(Status.IN_PROGRESS)) {
                orderEntity.setStatus(Status.SHIPPED);
                emailSenderService.sendMessage(orderEntity);
            } else if (orderEntity.getStatus().equals(Status.ACCEPTED)) {
                orderEntity.setStatus(Status.IN_PROGRESS);
                emailSenderService.sendMessage(orderEntity);
            } else {
                return Mono.error(new IllegalStateException("Status cannot be changed from " + orderEntity.getStatus()));
            }

            log.info("Updated status: {}", orderEntity.getStatus());

            return orderRepository.save(orderEntity)
                    .doOnSuccess(savedOrder -> log.info("Order saved with ID: {}", savedOrder.getId()))
                    .doOnError(error -> log.error("Error saving order", error));
        });
    }

public String getRecentlyConsumed(){

        return recentlyConsumed.toString();
}

}






