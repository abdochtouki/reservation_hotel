package com.code.demoza.services.admin.reservation;

import com.code.demoza.dto.ReservationResponceDto;

public interface ReservationService {
    ReservationResponceDto getAllReservations(int pageNumber);
    Boolean changeReservationStatus(Long id,String status);
}
