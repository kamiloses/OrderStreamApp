package com.group.kamiloses.orderstreamapp.repository;

import com.group.kamiloses.orderstreamapp.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User,String> {

    Mono<Boolean> existsByEmail(String email);
    Mono<Boolean> existsByEmailAndPassword(String email,String password);
    Mono<Void> deleteByEmailAndPassword(String email,String password);
}
