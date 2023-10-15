package com.example.gestionhoteliere;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class User {
    @Id
    private String id_user;
    private String name;
    private String capacity;
    private double price;
    private double surface;
    private String roomEquipement;
    private String entertaiment;
    private String other;
}