package com.example.gestionhoteliere.controllers;

import com.example.gestionhoteliere.models.Room;
import com.example.gestionhoteliere.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    // Endpoint pour ajouter une nouvelle chambre
    @PostMapping("/add")
    public Room addRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

     //Endpoint pour récupérer toutes les chambres
    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }


    @PutMapping("/update/{id}")
    public Room updateRoom(@PathVariable String id, @RequestBody Room updatedRoom) {
        Room existingRoom = roomRepository.findById(id).orElse(null);
        if (existingRoom == null) {
            // Gérer le cas où la chambre n'existe pas
            return null;
        }

        // Mettre à jour les champs de la chambre existante avec les données de "updatedRoom"
        existingRoom.setName(updatedRoom.getName());
        existingRoom.setCapacity(updatedRoom.getCapacity());
        existingRoom.setPrice(updatedRoom.getPrice());
        existingRoom.setSurface(updatedRoom.getSurface());
        existingRoom.setRoomEquipement(updatedRoom.getRoomEquipement());
        existingRoom.setEntertaiment(updatedRoom.getEntertaiment());
        existingRoom.setOther(updatedRoom.getOther());

        return roomRepository.save(existingRoom);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoom(@PathVariable String id) {
        Room existingRoom = roomRepository.findById(id).orElse(null);
        if (existingRoom != null) {
            roomRepository.delete(existingRoom);
        }
    }


}
