package com.group.kamiloses.orderstreamapp.service;

import com.group.kamiloses.orderstreamapp.config.exception.EmailAlreadyExistsException;
import com.group.kamiloses.orderstreamapp.config.exception.InvalidFieldException;
import com.group.kamiloses.orderstreamapp.dto.UserDto;
import com.group.kamiloses.orderstreamapp.entity.User;
import com.group.kamiloses.orderstreamapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.group.kamiloses.orderstreamapp.entity.Role.*;

@Service
public class AccountService {

    private final UserRepository userRepository;

    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> createAccount(UserDto userDto) {
        return userRepository.save(userDtoToEntity(userDto));
    }

    public Mono<Void> removeAccount(UserDto userDto) {
        return userRepository.deleteByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
    }


    public UserDto userEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setRole(ROLE_USER);
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }


    public User userDtoToEntity(UserDto userDto) {
        User userEntity = new User();
        userEntity.setName(userDto.getName());
        userEntity.setRole(ROLE_USER);
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        return userEntity;
        //todo zrób tak żeby nie przyjmowało nulla
    }


    public Mono<ServerResponse> validator(UserDto userDto) {

        if (userDto.getName() == null || !userDto.getName().matches("^[a-zA-Z ]+$")) {
            return Mono.error(new InvalidFieldException("Name is invalid"));
        }
        if (userDto.getPassword() == null || !userDto.getPassword().matches("^.+$")) {
            return Mono.error(new InvalidFieldException("Password is invalid"));
        }

        if (userDto.getEmail() == null|| !userDto.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")){
            return Mono.error(new InvalidFieldException("Email is invalid"));
        }

        return userRepository.existsByEmail(userDto.getEmail()).flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new EmailAlreadyExistsException("This email already exists"));
                    }
                    return ServerResponse.ok().bodyValue(userDto);
                }
        );
    }


    public Mono<Boolean> areFieldsMatchingAccount(UserDto userDto) {
        //String email = SecurityContextHolder.getContext().getAuthentication().getName();//todo zamien potem
        return userRepository.existsByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
//todo wstaw za customerDto.getEmail()


    }

}