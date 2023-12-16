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


    @PutMapping("/update/{name}")
    public Room updateRoom(@PathVariable String name, @RequestBody Room updatedRoom) {
        Room existingRoom = roomRepository.findRoomByName(name);
        if (existingRoom == null) {
            // Gérer le cas où la chambre n'existe pas
            return null;
        }
        if(updatedRoom.getName()!=null &&!existingRoom.getName().equals(updatedRoom.getName())){
            existingRoom.setName(updatedRoom.getName());

        }
        if(updatedRoom.getCapacity()!=0 && existingRoom.getCapacity()!=updatedRoom.getCapacity()){
            existingRoom.setCapacity(updatedRoom.getCapacity());

        } if(updatedRoom.getPrice()==0&&existingRoom.getPrice()!=updatedRoom.getPrice()){
            existingRoom.setPrice(updatedRoom.getPrice());
        } if(updatedRoom.getSurface()==0&&existingRoom.getSurface()!=updatedRoom.getSurface()){
            existingRoom.setPrice(updatedRoom.getCapacity());

        }if(updatedRoom.getRoomEquipement()!=null &&!existingRoom.getRoomEquipement().equals(updatedRoom.getRoomEquipement())){
            existingRoom.setRoomEquipement(updatedRoom.getRoomEquipement());

        } if(updatedRoom.getEntertaiment()!=null&&!(existingRoom.getEntertaiment().equals(updatedRoom.getEntertaiment()))){
            existingRoom.setEntertaiment(updatedRoom.getEntertaiment());

        } if(updatedRoom.getOther()!=null&&!(existingRoom.getOther().equals(updatedRoom.getOther()))){
            existingRoom.setOther(updatedRoom.getOther());

        }
        // Mettre à jour les champs de la chambre existante avec les données de "updatedRoom"

        roomRepository.save(existingRoom);
        return existingRoom ;
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteRoom(@PathVariable String name) {
        Room existingRoom = roomRepository.findRoomByName(name);
        if (existingRoom != null) {
            roomRepository.delete(existingRoom);
            return ResponseEntity.ok("room has been deleted");

        }
       return ResponseEntity.badRequest().body("Error");
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
