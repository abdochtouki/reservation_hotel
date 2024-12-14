package com.code.demoza.services.admin.rooms;

import com.code.demoza.dto.RoomDto;
import com.code.demoza.dto.RoomsRequestDto;

public interface RoomsService {
    Boolean postRoom(RoomDto roomDto);

    void deleteRoomById(Long id);
    Boolean updateRoom(Long id,RoomDto roomDto);

    RoomsRequestDto getAll(int pageNumber);

    RoomDto getRoomById(Long id);


}

