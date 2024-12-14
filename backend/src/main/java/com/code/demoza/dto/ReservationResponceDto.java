package com.code.demoza.dto;


import lombok.Data;

import java.util.List;

@Data
public class ReservationResponceDto {
    private Integer totalPages;

    private Integer pageNumber;
    private List<ReservationDto> reservations;
}
