package com.group.kamiloses.orderstreamapp.repository;

import com.group.kamiloses.orderstreamapp.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserEntity,String> {
    Mono<UserEntity> findUserByEmail(String email);
    Mono<Boolean> existsByEmail(String email);
    Mono<Boolean> existsByEmailAndPassword(String email,String password);
    Mono<Void> deleteByEmailAndPassword(String email,String password);
}
