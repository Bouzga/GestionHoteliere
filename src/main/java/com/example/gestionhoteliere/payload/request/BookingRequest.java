package com.example.gestionhoteliere.payload.request;

import com.example.gestionhoteliere.models.Booking;
import com.example.gestionhoteliere.models.Room;
import com.example.gestionhoteliere.models.User;
import com.example.gestionhoteliere.repositories.RoomRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    @NotNull(message = "Start date is required")
    private Date startDate;

    @NotNull(message = "End date is required")
    private Date endDate;

    private String idRoom;


    public boolean validBookingRequest(){
        Date currentDate = new Date();

        if (this.getStartDate() == null || this.getEndDate() == null ||this.getStartDate().after(this.getEndDate())||this.getStartDate().before(currentDate) || this.getEndDate().before(currentDate))
            return false;
        return true;
    }

}
