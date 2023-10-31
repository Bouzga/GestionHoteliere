package com.example.gestionhoteliere.controllers;

import com.example.gestionhoteliere.models.Booking;
import com.example.gestionhoteliere.models.Range;
import com.example.gestionhoteliere.models.User;
import com.example.gestionhoteliere.payload.request.BookingRequest;
import com.example.gestionhoteliere.repositories.BookingRepository;
import com.example.gestionhoteliere.repositories.RoomRepository;
import com.example.gestionhoteliere.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/add")
    public ResponseEntity<?> addBooking(@RequestBody BookingRequest bookingRequest ){
        if(!bookingRequest.validBookingRequest()) return ResponseEntity.badRequest().body("request not valid");
        var optionalRoom = roomRepository.findById(bookingRequest.getIdRoom());
        if(optionalRoom.isEmpty()) return  ResponseEntity.badRequest().body("room doesn't exist");
        var room = optionalRoom.get();
        var bookings =  bookingRepository.findByRoomId(room.getId());
        List<Range> dateRanges = bookings.stream()
                .map(booking -> new Range(booking.getStartDate(), booking.getEndDate()))
                .collect(Collectors.toList());
        var range = new Range(bookingRequest.getStartDate(),bookingRequest.getEndDate());
        var overLap = range.overlapsWith(dateRanges);
        if(overLap) return  ResponseEntity.badRequest().body("room is not available");

        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var booking = new Booking();
            try {

                booking.setRoom(room);
                booking.setStartDate(bookingRequest.getStartDate());
                booking.setEndDate(bookingRequest.getEndDate());
                booking.setUser(userRepository.findByUsername(userDetails.getUsername()).get());
                bookingRepository.save(booking);
                return ResponseEntity.ok(booking);
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception details
                return ResponseEntity.internalServerError().body("An error occurred while saving the booking.");
            }
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
    @GetMapping("/allBookings")
    public  List<Booking> getBookingByUser(){
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=userRepository.findByUsername(userDetails.getUsername()).get();

        return bookingRepository.findBookingByUser(user);


    }

}
