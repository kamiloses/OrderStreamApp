package com.group.kamiloses.orderstreamapp.handler;

import com.group.kamiloses.orderstreamapp.service.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

    private final ProductService productService;

    public ProductHandler(ProductService productService) {
        this.productService = productService;
    }

    public Mono<ServerResponse> getAllAvailableProducts(ServerRequest serverRequest){
        return productService.getAllProducts().collectList().flatMap(productEntity ->
                ServerResponse.ok().bodyValue(productEntity));


    }


}
