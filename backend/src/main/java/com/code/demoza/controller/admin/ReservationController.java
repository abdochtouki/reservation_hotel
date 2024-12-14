package com.code.demoza.controller.admin;


import com.code.demoza.bean.enums.ReservationStatus;
import com.code.demoza.services.admin.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

   @GetMapping("reservations/{pageNumber}")
    public ResponseEntity<?> getAllReservations(@PathVariable int pageNumber) {
        try{
            return ResponseEntity.ok(reservationService.getAllReservations(pageNumber));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
        }
    }

    @GetMapping("reservation/{id}/{status}")
    ResponseEntity<?> changeReservationStatus(@PathVariable Long id,@PathVariable String status) {
       Boolean success=reservationService.changeReservationStatus(id,status);

       if(success){
           return ResponseEntity.ok().build();
       }else{
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
       }
    }


}
