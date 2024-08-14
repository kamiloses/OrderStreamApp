package com.group.kamiloses.orderstreamapp.controller.jakishandler;

import com.group.kamiloses.orderstreamapp.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Controller
public class AccountRouter {


@Bean
    public RouterFunction<ServerResponse> routerFunction(AccountHandler accountHandler){
    return RouterFunctions.route().POST("/account",accountHandler::createAccount)
            .DELETE("/account",accountHandler::removeAccount)
            .build();


}



}
