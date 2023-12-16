package com.example.gestionhoteliere.payload.request;

import com.example.gestionhoteliere.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingPersoRequest {
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
   private String username;



}
