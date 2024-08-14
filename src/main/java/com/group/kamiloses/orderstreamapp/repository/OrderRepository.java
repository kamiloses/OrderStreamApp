package com.group.kamiloses.orderstreamapp.repository;

import com.group.kamiloses.orderstreamapp.entity.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderRepository extends ReactiveMongoRepository<Order,String> {
}
