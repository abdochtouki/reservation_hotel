package com.code.demoza.services.customer.room;

import com.code.demoza.dto.RoomsRequestDto;

public interface RoomServie {


    RoomsRequestDto getAvailableRooms(int pageNumber);
}
