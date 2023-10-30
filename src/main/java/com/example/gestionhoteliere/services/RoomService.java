package com.example.gestionhoteliere.services;

import com.example.gestionhoteliere.models.Range;
import com.example.gestionhoteliere.models.Room;
import com.example.gestionhoteliere.repositories.BookingRepository;
import com.example.gestionhoteliere.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    BookingRepository bookingRepository;
    RoomRepository roomRepository;
    public ResponseEntity<?> getRomByDate(Date startDate, Date endDate){
        var bookings =  bookingRepository.findAll();
        List<Range> dateRanges = bookings.stream()
                .map(booking -> new Range(booking.getStartDate(), booking.getEndDate()))
                .collect(Collectors.toList());
        var range = new Range(startDate,endDate);
        var overLap = range.overlapsWith(dateRanges);
        List<Room> availableRooms = roomRepository.findAll().stream()
                .filter(room -> !overLap)
                .collect(Collectors.toList());




        return ResponseEntity.ok(bookings);
    }
}
