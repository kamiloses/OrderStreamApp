package com.group.kamiloses.orderstreamapp.service;

import com.group.kamiloses.orderstreamapp.dto.MakeAnOrderDto;
import com.group.kamiloses.orderstreamapp.dto.ProductDto;
import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
import com.group.kamiloses.orderstreamapp.entity.ProductEntity;
import com.group.kamiloses.orderstreamapp.other.Status;
import com.group.kamiloses.orderstreamapp.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final AccountService accountService;
    private final OrderRepository orderRepository;

    public OrderService(AccountService accountService, OrderRepository orderRepository) {
        this.accountService = accountService;
        this.orderRepository = orderRepository;
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

    public Mono<OrderEntity> modifyOrderStatus(String orderId) {
        return orderRepository.findById(orderId).flatMap(orderEntity -> {
                    if (orderEntity.getStatus().equals(Status.IN_PROGRESS)) {orderEntity.setStatus(Status.SHIPPED);}
                       //invokeEmailSender;
                    if (orderEntity.getStatus().equals(Status.ACCEPTED)) {
                         //invokeEmailSender;
                        orderEntity.setStatus(Status.IN_PROGRESS);}
                    else return Mono.error(new IllegalStateException("Status cannot be changed"));
                 return orderRepository.save(orderEntity);})
                .switchIfEmpty(Mono.error(() -> new Exception("Order not found"))

                );


    }


}