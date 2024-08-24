package com.group.kamiloses.orderstreamapp.handler;

import com.group.kamiloses.orderstreamapp.controller.exception.InvalidFieldException;
import com.group.kamiloses.orderstreamapp.dto.UserDto;
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
        return serverRequest.bodyToMono(UserDto.class)
                .flatMap(userDto -> accountService.validator(userDto)
                        .then(accountService.createAccount(userDto)))
                .flatMap(request -> ServerResponse.ok().bodyValue(request));

    }

    public Mono<ServerResponse> removeAccount(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserDto.class)
                .flatMap(userDto ->
                        accountService.areFieldsMatchingAccount(userDto).flatMap(matches -> {
                            if (matches) {
                                return accountService.removeAccount(userDto)
                                        .then(ServerResponse.ok().bodyValue("Account has been deleted"));}
                             else {
                                return Mono.error(new InvalidFieldException("Provided credentials do not match"));}
                        }));


    }

}


