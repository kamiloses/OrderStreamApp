package com.group.kamiloses.orderstreamapp.other;

import com.group.kamiloses.orderstreamapp.repository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
    @Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {

   private final CustomerRepository customerRepository;

    public UniqueEmailValidator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



        @Override
        public boolean isValid(String email, ConstraintValidatorContext context) {

           return customerRepository.existsByEmail(email).block();
        }
    }