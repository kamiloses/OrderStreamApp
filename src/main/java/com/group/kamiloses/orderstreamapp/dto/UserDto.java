package com.group.kamiloses.orderstreamapp.dto;

import com.group.kamiloses.orderstreamapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private Role role;
    private String email;

    private String password;

}
