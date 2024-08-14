package com.group.kamiloses.orderstreamapp.repository;

import com.group.kamiloses.orderstreamapp.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
}
