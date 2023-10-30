package com.example.gestionhoteliere.repositories;

import com.example.gestionhoteliere.models.Booking;
import com.example.gestionhoteliere.models.Room;
import com.example.gestionhoteliere.payload.request.BookingRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking,String> {
    List<Booking> findByRoomId(String id);

}
