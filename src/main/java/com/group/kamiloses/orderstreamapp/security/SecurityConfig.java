package com.group.kamiloses.orderstreamapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
    return http.authorizeExchange(exchange->exchange.pathMatchers("/account").permitAll()
                    .anyExchange().authenticated()
            ).httpBasic(Customizer.withDefaults())
            .build();


}
    @Bean
    PasswordEncoder passwordEncoder() {
          //todo BCRYPT
        return NoOpPasswordEncoder.getInstance();
    }

}
