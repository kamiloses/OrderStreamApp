package com.group.kamiloses.orderstreamapp.security;

import com.group.kamiloses.orderstreamapp.entity.UserEntity;
import com.group.kamiloses.orderstreamapp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    public CustomReactiveUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Mono<UserDetails> findByUsername(String email) {
return  userRepository.findUserByEmail(email)
        .switchIfEmpty(Mono.error(new UsernameNotFoundException("User was not found")))
        .map(this::createUser);


    }



private UserDetails createUser(UserEntity user){

    return new User(user.getEmail(),user.getPassword(),roleToGrantedAuthority(user));
}








    private List<GrantedAuthority> roleToGrantedAuthority(UserEntity user) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return list;
    }


}
