package com.example.gestionhoteliere.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document
public class Booking {
    @Id
    private String id_booking;
    private Date startDate;
    private Date endDate;
    private Room room;
}
