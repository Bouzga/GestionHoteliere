package com.example.gestionhoteliere.controller;

import com.example.gestionhoteliere.entities.Booking;
import com.example.gestionhoteliere.entities.User;
import com.example.gestionhoteliere.repository.BookingRepository;
import com.example.gestionhoteliere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
