package com.example.gestionhoteliere.controller;

import com.example.gestionhoteliere.entities.Room;
import com.example.gestionhoteliere.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    // Endpoint pour ajouter une nouvelle chambre
    @PostMapping("/add")
    public Room addRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    // Endpoint pour récupérer toutes les chambres
    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
