package com.example.gestionhoteliere.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String email;
    private String firstName;
    private String lastName;

    private int age;

    private String phone;
}
