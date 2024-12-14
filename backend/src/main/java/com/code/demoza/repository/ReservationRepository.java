package com.code.demoza.repository;

import com.code.demoza.bean.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
     Page<Reservation> findAllByUserId(Pageable pageable,Long userId);

     @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId " +
             "AND ((r.checkInDate BETWEEN :checkInDate AND :checkOutDate) " +
             "OR (r.checkOutDate BETWEEN :checkInDate AND :checkOutDate) " +
             "OR (r.checkInDate <= :checkInDate AND r.checkOutDate >= :checkOutDate))")
     List<Reservation> findByRoomAndDatesOverlap(@Param("roomId") Long roomId,
                                                 @Param("checkInDate") LocalDate checkInDate,
                                                 @Param("checkOutDate") LocalDate checkOutDate);

}
