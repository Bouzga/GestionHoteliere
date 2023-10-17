package com.example.gestionhoteliere.repository;

import com.example.gestionhoteliere.entities.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking,String> {
}
