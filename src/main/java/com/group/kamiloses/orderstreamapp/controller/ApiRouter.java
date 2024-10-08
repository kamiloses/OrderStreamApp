    package com.group.kamiloses.orderstreamapp.controller;

    import com.group.kamiloses.orderstreamapp.handler.AccountHandler;
    import com.group.kamiloses.orderstreamapp.handler.OrderHandler;
    import com.group.kamiloses.orderstreamapp.handler.ProductHandler;
    import org.springframework.context.annotation.Bean;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.reactive.function.server.RouterFunction;
    import org.springframework.web.reactive.function.server.RouterFunctions;
    import org.springframework.web.reactive.function.server.ServerResponse;

    @Controller
    public class ApiRouter {


    @Bean
        public RouterFunction<ServerResponse> routerFunction(AccountHandler accountHandler, OrderHandler orderHandler, ProductHandler productHandler){
        return RouterFunctions.route().POST("/createAccount",accountHandler::createAccount)
                .DELETE("/removeAccount",accountHandler::removeAccount)
                .POST("/makeAnOrder",orderHandler::makeAnOrder)
                .GET("/products",productHandler::getAllAvailableProducts)
                .GET("/orders",orderHandler::getAllOrders)
                .PATCH("/modifyOrderStatus/{id}",orderHandler::modifyOrderStatus)
                .build();


    }



    }
