package com.code.demoza.controller.admin;

import com.code.demoza.dto.RoomDto;
import com.code.demoza.services.admin.rooms.RoomsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class RoomController {


    @Autowired
    private RoomsService roomService;

    @PostMapping("/room")
    public ResponseEntity<?> postRoom(@RequestBody RoomDto roomDto) {

        Boolean success = roomService.postRoom(roomDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity<?> getAllRooms(@PathVariable int pageNumber) {
        return ResponseEntity.ok(roomService.getAll(pageNumber));
    }

    @GetMapping("room/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(roomService.getRoomById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    @PutMapping("/room/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody RoomDto roomDto) {
        boolean success = roomService.updateRoom(id, roomDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // BAD_REQUEST remplacé par NOT_FOUND si l'entité est introuvable
        }
    }

    @DeleteMapping("room/{id}")
    public ResponseEntity<?> deleteRoomById(@PathVariable Long id) {
        try{
            roomService.deleteRoomById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

