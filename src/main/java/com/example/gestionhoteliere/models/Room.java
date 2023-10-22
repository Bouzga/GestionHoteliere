package com.example.gestionhoteliere.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Room {
    @Id
    private String id_room;
    private String name;
    private String capacity;
    private double price;
    private double surface;
    private String roomEquipement;
    private String entertaiment;
    private String other;
}
