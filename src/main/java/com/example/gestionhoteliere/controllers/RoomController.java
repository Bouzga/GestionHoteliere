package com.example.gestionhoteliere.controllers;

import com.example.gestionhoteliere.models.Booking;
import com.example.gestionhoteliere.models.Range;
import com.example.gestionhoteliere.models.Room;
import com.example.gestionhoteliere.payload.request.SearchRoomRequest;
import com.example.gestionhoteliere.repositories.BookingRepository;
import com.example.gestionhoteliere.repositories.RoomRepository;
import com.example.gestionhoteliere.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth/rooms")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingRepository bookingRepository;


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

    @PostMapping("/search")
    public ResponseEntity<?> SearchRoom(@RequestBody SearchRoomRequest searchRoomRequest) {
        var availableRooms = new ArrayList<Room>();

        Range range = new Range(searchRoomRequest.getStartDate(), searchRoomRequest.getEndDate());
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            var bookings = bookingRepository.findByRoomId(room.getId());
            List<Range> dateRanges = bookings.stream()
                    .map(booking -> new Range(booking.getStartDate(), booking.getEndDate()))
                    .collect(Collectors.toList());
            var overlap = range.overlapsWith(dateRanges);
            if (!overlap) availableRooms.add(room);


        }

        List<Room> availableRoomsWithCapacity = availableRooms.stream()
                .filter(room -> room.getCapacity() == searchRoomRequest.getCapacity())
                .collect(Collectors.toList());
        return ResponseEntity.ok(availableRoomsWithCapacity);
    }
}
