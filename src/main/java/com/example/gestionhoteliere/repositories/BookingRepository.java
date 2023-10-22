package com.example.gestionhoteliere.repositories;

import com.example.gestionhoteliere.models.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking,String> {
}
