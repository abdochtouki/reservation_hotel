package com.code.demoza.controller.customer;

import com.code.demoza.dto.ReservationDto;
import com.code.demoza.services.customer.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;


     @PostMapping("/book")
    public ResponseEntity<?> postReservation(@RequestBody ReservationDto reservationdto) {
        boolean success=bookingService.postReservation(reservationdto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }
    @GetMapping("/bookings/{userId}/{pageNumber}")
    public ResponseEntity<?> findAllByUserId(@PathVariable Long userId,@PathVariable int pageNumber){
         try{
             return ResponseEntity.ok(bookingService.findAllByUserId(userId,pageNumber));
         }catch(Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong .");
         }
    }
}
