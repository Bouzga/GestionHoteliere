package com.example.gestionhoteliere.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Booking {
    @Id
    private Long id_booking;
    private Date startDate;
    private Date endDate;
    private Room room;
}
