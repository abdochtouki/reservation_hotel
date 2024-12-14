package com.code.demoza.services.customer.booking;

import com.code.demoza.dto.ReservationDto;
import com.code.demoza.dto.ReservationResponceDto;

public interface BookingService {
    Boolean postReservation(ReservationDto reservationDto);
    ReservationResponceDto findAllByUserId(Long userId, int pageNumber);
}
