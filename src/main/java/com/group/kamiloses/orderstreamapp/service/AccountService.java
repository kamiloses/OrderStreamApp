package com.group.kamiloses.orderstreamapp.service;

import com.group.kamiloses.orderstreamapp.controller.InvalidFieldException;
import com.group.kamiloses.orderstreamapp.dto.CustomerDto;
import com.group.kamiloses.orderstreamapp.entity.Customer;
import com.group.kamiloses.orderstreamapp.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    private final CustomerRepository customerRepository;

    public AccountService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> createAccount(CustomerDto customerDto){
        return customerRepository.save(customerDtoToEntity(customerDto));
    }

 public CustomerDto customerEntityToDto(Customer customer){
     CustomerDto customerDto = new CustomerDto();
     customerDto.setId(customer.getId());
     customerDto.setName(customer.getName());
     customerDto.setEmail(customer.getEmail());
     customerDto.setPassword(customer.getPassword());
 return customerDto;}



  public Customer customerDtoToEntity(CustomerDto customerDto){
      Customer customerEntity= new Customer();
      customerEntity.setName(customerDto.getName());
      customerEntity.setEmail(customerDto.getEmail());
      customerEntity.setPassword(customerDto.getPassword());
      return customerEntity;
      //todo zrób tak żeby nie przyjmowało nulla
    }


    public Mono<ServerResponse> validator(CustomerDto customerDto) {

        if (customerDto.getName().isBlank()||!customerDto.getName().matches("^[a-zA-Z ]+$")) {
            return Mono.error(new InvalidFieldException("Name is invalid"));
        }
        if (customerDto.getPassword().isBlank()||!customerDto.getPassword().matches("^[a-zA-Z ]+$")){
        return Mono.error(new InvalidFieldException("Password is invalid"));   }

        if (customerDto.getEmail().isBlank()){
            return Mono.error(new InvalidFieldException("Email is invalid"));}

        return customerRepository.existsByEmail(customerDto.getEmail()).flatMap(exists-> {
      if (exists){
          return Mono.error(new InvalidFieldException("This email already exists"));
      }
        return ServerResponse.ok().bodyValue(customerDto);}
        );}

    }