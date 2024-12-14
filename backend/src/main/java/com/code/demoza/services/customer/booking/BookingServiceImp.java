package com.code.demoza.services.customer.booking;


import com.code.demoza.bean.Reservation;
import com.code.demoza.bean.Room;
import com.code.demoza.bean.User;
import com.code.demoza.bean.enums.ReservationStatus;
import com.code.demoza.dto.ReservationDto;
import com.code.demoza.dto.ReservationResponceDto;
import com.code.demoza.repository.ReservationRepository;
import com.code.demoza.repository.RoomRepository;
import com.code.demoza.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImp implements BookingService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public static final int SEARCH_RESULT_PER_PAGE=4;

    public Boolean postReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom = roomRepository.findById(reservationDto.getRoomId());

        if (optionalUser.isPresent() && optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            User user = optionalUser.get();

            LocalDate today = LocalDate.now();
            if (reservationDto.getCheckInDate().isBefore(today) || reservationDto.getCheckOutDate().isBefore(today)) {
                return false;
            }

            if (reservationDto.getCheckOutDate().isBefore(reservationDto.getCheckInDate())) {
                return false;
            }

            if (!room.getRoomDto().isAvailable()) {
                return false;
            }

            Reservation reservation = new Reservation();
            reservation.setUser(user);
            reservation.setRoom(room);
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setStatus(ReservationStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
            reservation.setPrice(room.getPrice() * days);

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    public ReservationResponceDto findAllByUserId(Long userId,int pageNumber){
        Pageable pageable= PageRequest.of(pageNumber,SEARCH_RESULT_PER_PAGE);

        Page<Reservation> reservationPage= reservationRepository.findAllByUserId(pageable,userId);

        ReservationResponceDto reservationResponceDto = new ReservationResponceDto();
        reservationResponceDto.setReservations(reservationPage.stream().map(Reservation::reservationdto)
                .collect(Collectors.toList()));
        reservationResponceDto.setPageNumber(reservationPage.getNumber());
        reservationResponceDto.setTotalPages(reservationPage.getTotalPages());
        return reservationResponceDto;

    }


}
