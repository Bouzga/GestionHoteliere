package com.example.gestionhoteliere.repository;

import com.example.gestionhoteliere.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, Long> {
}
