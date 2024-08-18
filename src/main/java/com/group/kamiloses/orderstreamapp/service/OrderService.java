package com.group.kamiloses.orderstreamapp.service;

import com.group.kamiloses.orderstreamapp.dto.MakeAnOrderDto;
import com.group.kamiloses.orderstreamapp.dto.ProductDto;
import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
import com.group.kamiloses.orderstreamapp.entity.ProductEntity;
import com.group.kamiloses.orderstreamapp.kafka.KafkaProducer;
import com.group.kamiloses.orderstreamapp.other.Status;
import com.group.kamiloses.orderstreamapp.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    private final AccountService accountService;
    private final OrderRepository orderRepository;
    private final KafkaProducer kafkaProducer;

    public OrderService(AccountService accountService, OrderRepository orderRepository, KafkaProducer kafkaProducer) {
        this.accountService = accountService;
        this.orderRepository = orderRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public Mono<OrderEntity> makeAnOrder(MakeAnOrderDto orderDto) {
        return accountService.getLoggedUserDetails().map(
                userEntity -> {
                    OrderEntity orderEntity = new OrderEntity();
                    orderEntity.setUserId(userEntity.getId());
                    orderEntity.setOrderDate(new Date());
                    orderEntity.setProductEntities(convertProductList(orderDto.getProducts()));
                    orderEntity.setStatus(Status.ACCEPTED);
                    return orderEntity;
                }).flatMap(orderRepository::save);
    }

    private List<ProductEntity> convertProductList(List<ProductDto> allProducts) {
        return allProducts.stream().map(product -> new ProductEntity(null, product.getName())).toList();


    }


    public Flux<OrderEntity> getAllAvailableOrders() {
        return orderRepository.findAll()
                .filter(orderEntity -> !orderEntity.getStatus().equals(Status.SHIPPED));
    }

    public Mono<Void> modifyOrderStatus(String orderId) {
        return orderRepository.findById(orderId)
                .flatMap(kafkaProducer::sendMessage)
                .switchIfEmpty(Mono.error(() -> new Exception("Order not found")));
    }


    }


