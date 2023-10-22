package com.example.gestionhoteliere.payload.request;


import com.example.gestionhoteliere.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserRequest {

    private String username;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
