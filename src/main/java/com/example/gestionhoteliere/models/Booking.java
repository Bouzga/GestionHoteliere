package com.example.gestionhoteliere.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "booking")
public class Booking {
    @Id
    private String id;
    private Date startDate;
    private Date endDate;
    @DBRef
    private User user;

    @DBRef
    private Room room ;
}
