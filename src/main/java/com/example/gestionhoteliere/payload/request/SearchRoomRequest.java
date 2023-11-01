package com.example.gestionhoteliere.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRoomRequest {
    private Date startDate;
    private Date endDate;
    private int capacity;
}
