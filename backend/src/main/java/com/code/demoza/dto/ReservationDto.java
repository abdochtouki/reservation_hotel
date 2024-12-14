package com.code.demoza.dto;

import com.code.demoza.bean.enums.ReservationStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {

    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Long price;
    private ReservationStatus status;
    private Long roomId;
    private String roomName;
    private String roomType;
    private Long userId;
    private String userName;

}
