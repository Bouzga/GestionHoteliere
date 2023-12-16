package com.example.gestionhoteliere.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "room")
public class Room {
    @Id
    private String id;
    @NotBlank(message = "Name cannot be empty")
    @Indexed(unique = true)
    private String name;
    private int capacity;
    private double price;
    private double surface;
    private String roomEquipement;
    private String entertaiment;
    private String other;
}
