package com.code.demoza.dto;


import lombok.Data;

import java.util.List;

@Data
public class RoomsRequestDto {
    private List<RoomDto> roomDtolist;
    private Integer totalPages;
    private Integer pageNumber;


}
