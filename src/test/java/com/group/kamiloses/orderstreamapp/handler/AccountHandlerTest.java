package com.group.kamiloses.orderstreamapp.handler;

import com.group.kamiloses.orderstreamapp.entity.UserEntity;
import com.group.kamiloses.orderstreamapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static com.group.kamiloses.orderstreamapp.other.Role.ROLE_USER;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@DirtiesContext
@AutoConfigureWebTestClient
    class AccountHandlerTest {

    @Autowired
    WebTestClient webTestClient;

     @Autowired
    UserRepository userRepository;
    List<UserEntity> allUsers = Arrays.asList(
            new UserEntity(null, "John", ROLE_USER, "John@gmail.com", "John123"),
            new UserEntity(null, "Adam", ROLE_USER, "Adam@gmail.com", "Adam123"));


    UserEntity user = new UserEntity(null, "Philip", ROLE_USER, "Philip@gmail.com", "Philip");
    @BeforeEach
    public void setUp() throws InterruptedException {

        userRepository.deleteAll().then(Mono.just(user)).flatMap(userRepository::save).subscribe();
      Thread.sleep(1000);
    }




     @Test
     public void shouldCheckCreateAccountMethod(){
         UserEntity user = new UserEntity(null, "Kamil", ROLE_USER, "kamiloses@gmail.com", "kamil");
         webTestClient.post().uri("/account").bodyValue(user).exchange().expectStatus().isOk();



     }

    @Test
    public void shouldThrowEmailAlreadyExistsEx(){
        webTestClient.post().uri("/account").bodyValue(user).exchange().expectStatus().isBadRequest();


    }





}