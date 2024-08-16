package com.group.kamiloses.orderstreamapp.repository;

import com.group.kamiloses.orderstreamapp.entity.ProductEntity;
import com.group.kamiloses.orderstreamapp.entity.UserEntity;
import com.group.kamiloses.orderstreamapp.other.Product;
import com.group.kamiloses.orderstreamapp.other.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static com.group.kamiloses.orderstreamapp.other.Role.ROLE_USER;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@DirtiesContext
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    List<UserEntity> allUsers = Arrays.asList(
            new UserEntity(null, "John", ROLE_USER, "John@gmail.com", "John123"),
            new UserEntity(null, "Adam", ROLE_USER, "Adam@gmail.com", "Adam123"),
            new UserEntity(null, "Philip", ROLE_USER, "Philip@gmail.com", "Philip"));


    @BeforeEach
    public void setUp() throws InterruptedException {

        userRepository.deleteAll().thenMany(Flux.fromIterable(allUsers)).flatMap(userRepository::save)
                .doOnNext(product -> System.out.println("saved : " + product)).subscribe();
        Thread.sleep(2000);


    }

    @Test
    public void shouldCheckIfProductsAreBeingSavedToDB() throws InterruptedException {
        StepVerifier.create(userRepository.findAll()).expectSubscription().expectNextCount(3).verifyComplete();

    }

    @Test
    public void shouldCheckIfFindUserByEmailWorks() {
        StepVerifier.create(userRepository.findUserByEmail("John@gmail.com")).expectSubscription()
                .expectNextCount(1).verifyComplete();

        StepVerifier.create(userRepository.findUserByEmail("test@gmail.com")).expectSubscription()
                .expectNextCount(0).verifyComplete();

    }


}