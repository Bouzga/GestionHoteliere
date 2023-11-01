package com.example.gestionhoteliere.repositories;

import com.example.gestionhoteliere.models.Booking;
import com.example.gestionhoteliere.models.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
        List<Room> findRoomByCapacity(int capacity);
}
