package com.example.gestionhoteliere.controllers;

import com.example.gestionhoteliere.models.Booking;
import com.example.gestionhoteliere.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;

    //Endpoint pour ajouter un user
    @PostMapping("/add")
    public Booking addBooking(@RequestBody Booking booking){
        return bookingRepository.save(booking);
    }

    @GetMapping("/All")
    public List<Booking> getAllBooking(){
        return bookingRepository.findAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable String id, @RequestBody Booking updatedBooking) {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking == null) {
            // Gérer le cas où la réservation n'existe pas
            return ResponseEntity.notFound().build();
        }

        // Mettre à jour les champs de la réservation existante avec les données de "updatedBooking"
        existingBooking.setStartDate(updatedBooking.getStartDate());
        existingBooking.setEndDate(updatedBooking.getEndDate());
        existingBooking.setRoom(updatedBooking.getRoom());

        Booking updatedBookingResult = bookingRepository.save(existingBooking);
        return ResponseEntity.ok(updatedBookingResult);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteBooking(@PathVariable String id) {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking != null) {
            bookingRepository.delete(existingBooking);
        }
    }

}
