package com.code.demoza.services.admin.rooms;


import com.code.demoza.bean.Room;
import com.code.demoza.dto.RoomDto;
import com.code.demoza.dto.RoomsRequestDto;
import com.code.demoza.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RoomsServiceImp implements RoomsService {

    @Autowired
    private final RoomRepository roomRepository;


    public Boolean postRoom(RoomDto roomDto) {
        try {
            Room room = new Room();
            room.setName(roomDto.getName());
            room.setType(roomDto.getType());
            room.setPrice(roomDto.getPrice());
            room.setAvailable(true);
            roomRepository.save(room);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    @Override
    public Boolean updateRoom(Long id, RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room existingRoom = optionalRoom.get();
            existingRoom.setName(roomDto.getName());
            existingRoom.setType(roomDto.getType());
            existingRoom.setPrice(roomDto.getPrice());
            roomRepository.save(existingRoom);
            return true; // Mise à jour réussie
        } else {
            return false; // Chambre introuvable
        }
    }



    public RoomsRequestDto getAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Room> rooms = roomRepository.findAll(pageable);

        RoomsRequestDto roomsDto = new RoomsRequestDto();
        roomsDto.setPageNumber(rooms.getPageable().getPageNumber());
        roomsDto.setTotalPages(rooms.getTotalPages());
        roomsDto.setRoomDtolist(rooms.stream().map(Room::getRoomDto).collect(Collectors.toList()));
        return roomsDto;
    }

    public RoomDto getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            return room.get().getRoomDto();
        } else {
            throw new EntityNotFoundException("Room not found");
        }

    }
    public void deleteRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            roomRepository.delete(room.get());
        }else{
            throw new EntityNotFoundException("Room not found");
        }
    }

}


