package com.group.kamiloses.orderstreamapp.repository;

import com.group.kamiloses.orderstreamapp.entity.Customer;
import com.group.kamiloses.orderstreamapp.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer,String> {

 Mono<Boolean> existsByEmail(String email);
}
