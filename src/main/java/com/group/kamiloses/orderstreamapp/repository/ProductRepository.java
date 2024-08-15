package com.group.kamiloses.orderstreamapp.repository;

import com.group.kamiloses.orderstreamapp.entity.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity,String> {



}
