//package com.group.kamiloses.orderstreamapp.service;
//
//import com.group.kamiloses.orderstreamapp.repository.CustomerRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@Component
//public class CustomUserDetailsService implements UserDetailsService {
//
//
//    private final CustomerRepository userRepository;
//
//    public CustomUserDetailsService(CustomerRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        UserEntity userEntity = userRepository.findUserEntityByEmail(email).orElseThrow(()->new UsernameNotFoundException("user with this email was not found"));
//
//
//        return new User(userEntity.getEmail(), userEntity.getPassword(), grantedAuthorities(userEntity));
//    }
//
//
//    private Collection<GrantedAuthority> grantedAuthorities(UserEntity user) {
//        return Stream.of(user).map(user1 -> new SimpleGrantedAuthority(user1.getRole().toString())).collect(Collectors.toList());
//        //each user will have only 1 role(user or admin)
//    }
//
//
//}