package com.group.kamiloses.orderstreamapp.entity;

import com.group.kamiloses.orderstreamapp.other.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String id;
    private String name;
    private Role role;
    private String email;
    private String password;
}
