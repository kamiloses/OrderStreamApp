//package com.group.kamiloses.orderstreamapp.handler;
//
//import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
//import com.group.kamiloses.orderstreamapp.other.Status;
//import com.group.kamiloses.orderstreamapp.repository.OrderRepository;
//import com.group.kamiloses.orderstreamapp.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//@DirtiesContext
//@AutoConfigureWebTestClient
//class OrderHandlerTest {
//
//
//
//    @Autowired
//    WebTestClient webTestClient;
//
//    @Autowired
//    OrderRepository orderRepository;
//    @Autowired
//    UserRepository userRepository;
//
//
//
//
//
//    @BeforeEach
//    public void setUp() throws InterruptedException {
//
//        userRepository.deleteAll().then(Mono.just(user)).flatMap(userRepository::save).subscribe();
//        Thread.sleep(1000);
//    }
//
//@Test
//public void shouldCheck(){
//    OrderEntity order1 = new OrderEntity();
//    order1.setStatus(Status.ACCEPTED);
//
//
//}
//
//
//
//
//
//
//
//}