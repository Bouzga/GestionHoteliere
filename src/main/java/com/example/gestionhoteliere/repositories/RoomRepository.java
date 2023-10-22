package com.example.gestionhoteliere.repositories;

import com.example.gestionhoteliere.models.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {

}
