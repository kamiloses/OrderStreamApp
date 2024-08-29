package com.group.kamiloses.orderstreamapp.handler;

import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
import com.group.kamiloses.orderstreamapp.entity.UserEntity;
import com.group.kamiloses.orderstreamapp.other.Role;
import com.group.kamiloses.orderstreamapp.other.Status;
import com.group.kamiloses.orderstreamapp.repository.OrderRepository;
import com.group.kamiloses.orderstreamapp.repository.UserRepository;
import com.group.kamiloses.orderstreamapp.service.OrderService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.group.kamiloses.orderstreamapp.other.Role.ROLE_ADMIN;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@DirtiesContext
@AutoConfigureWebTestClient
class OrderHandlerTest {



    @Autowired
    WebTestClient webTestClient;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderService orderService;






    @BeforeEach
    public void setUp() throws InterruptedException {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId("1");
        orderEntity.setStatus(Status.ACCEPTED);
        orderRepository.deleteAll().then(Mono.just(orderEntity)).flatMap(orderRepository::save).subscribe();
        userRepository.save(new UserEntity(null,"admin", ROLE_ADMIN,"admin@gmail.com","admin123")).subscribe();
        Thread.sleep(1000);
    }

    @Test
    public void shouldCheckModifyOrderStatus() {
        webTestClient
                .patch()
                .uri("/modifyOrderStatus/1").headers(httpHeaders -> httpHeaders.setBasicAuth("admin@gmail.com","admin123"))
                .exchange()
                .expectStatus().is5xxServerError();
                //todo popraw potem by poprawnie status sie wyświetlał przy modyfikacji zamówienia bo ogólnie transakcja sie udaje
    }











}