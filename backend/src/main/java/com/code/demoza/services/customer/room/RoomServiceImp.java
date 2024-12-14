package com.code.demoza.services.customer.room;


import com.code.demoza.bean.Room;
import com.code.demoza.dto.RoomsRequestDto;
import com.code.demoza.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImp implements RoomServie{

    private final RoomRepository roomRepository;


    public RoomsRequestDto getAvailableRooms(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Room> rooms = roomRepository.findByAvailable(true,pageable);

        RoomsRequestDto roomsDto = new RoomsRequestDto();
        roomsDto.setPageNumber(rooms.getPageable().getPageNumber());
        roomsDto.setTotalPages(rooms.getTotalPages());
        roomsDto.setRoomDtolist(rooms.stream().map(Room::getRoomDto).collect(Collectors.toList()));
        return roomsDto;
    }


}
