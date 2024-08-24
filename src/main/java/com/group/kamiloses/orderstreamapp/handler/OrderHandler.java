package com.group.kamiloses.orderstreamapp.handler;

import com.group.kamiloses.orderstreamapp.dto.MakeAnOrderDto;
import com.group.kamiloses.orderstreamapp.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class OrderHandler {


    private final OrderService orderService;

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public Mono<ServerResponse> makeAnOrder(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MakeAnOrderDto.class)
                .flatMap(orderService::makeAnOrder).flatMap(request -> ServerResponse.ok().bodyValue(request));


    }


    public Mono<ServerResponse> getAllOrders(ServerRequest serverRequest) {
        return ServerResponse.ok().body(orderService.getAllAvailableOrders(), OrderService.class);
    }


    public Mono<ServerResponse> modifyOrderStatus(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");

        return orderService.modifyOrderStatus(id)
                .then(ServerResponse.ok().bodyValue("Status modified"));
    }
}
