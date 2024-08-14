package com.group.kamiloses.orderstreamapp.dto;

import com.group.kamiloses.orderstreamapp.other.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String id;
    private String name;

    private String email;

    private String password;

}
