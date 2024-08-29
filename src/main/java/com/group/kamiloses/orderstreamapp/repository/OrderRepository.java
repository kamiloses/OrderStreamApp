package com.group.kamiloses.orderstreamapp.repository;

import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
import com.group.kamiloses.orderstreamapp.other.Status;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface OrderRepository extends ReactiveMongoRepository<OrderEntity,String> {

    Flux<OrderEntity> findByStatus(Status status);



}
