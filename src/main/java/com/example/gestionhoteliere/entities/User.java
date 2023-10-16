package com.example.gestionhoteliere.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class User {
    @Id
    private Long id_user;
    private String firstName;
    private String lastNAme;
    private String email;
    private String password;
    private String numPhone;
    private Role role;

}