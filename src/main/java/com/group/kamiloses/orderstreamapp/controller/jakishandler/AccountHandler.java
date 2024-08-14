package com.group.kamiloses.orderstreamapp.controller.jakishandler;

import com.group.kamiloses.orderstreamapp.dto.CustomerDto;
import com.group.kamiloses.orderstreamapp.service.AccountService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;



@Component
public class AccountHandler {


    private final AccountService accountService;


    public AccountHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    public Mono<ServerResponse> createAccount(ServerRequest serverRequest) {
    return serverRequest.bodyToMono(CustomerDto.class)
            .flatMap(customerDto->accountService.validator(customerDto)
                    .then(accountService.createAccount(customerDto)))
            .flatMap(request->ServerResponse.ok().bodyValue(request));

    }
}

