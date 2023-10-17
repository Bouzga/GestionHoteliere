package com.example.gestionhoteliere.repository;

import com.example.gestionhoteliere.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {

}
