package com.code.demoza.controller.customer;


import com.code.demoza.services.customer.room.RoomServie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("customerRoomController")
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class RoomController {

    private  final RoomServie roomServie;

    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity<?> getAvailableRooms(@PathVariable int pageNumber){
        return ResponseEntity.ok(roomServie.getAvailableRooms(pageNumber));
    }
}
