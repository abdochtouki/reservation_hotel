package com.code.demoza.services.admin.reservation;


import com.code.demoza.bean.Reservation;
import com.code.demoza.bean.Room;
import com.code.demoza.bean.enums.ReservationStatus;
import com.code.demoza.dto.ReservationResponceDto;
import com.code.demoza.repository.ReservationRepository;
import com.code.demoza.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReservationServiceImp implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public static final int SEARCH_RESULT_PER_PAGE = 4;

    public ReservationResponceDto  getAllReservations(int pageNumber) {
        Pageable pageable= PageRequest.of(pageNumber,SEARCH_RESULT_PER_PAGE);

        Page<Reservation> reservationPage= reservationRepository.findAll(pageable);

        ReservationResponceDto reservationResponceDto = new ReservationResponceDto();
        reservationResponceDto.setReservations(reservationPage.stream().map(Reservation::reservationdto)
                .collect(Collectors.toList()));
        reservationResponceDto.setPageNumber(reservationPage.getNumber());
        reservationResponceDto.setTotalPages(reservationPage.getTotalPages());
        return reservationResponceDto;
    }
    public Boolean changeReservationStatus(Long id, String status) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            Room room = reservation.getRoom();

            if (Objects.equals(status, "Approve")) {
                reservation.setStatus(ReservationStatus.APPROVED);
                room.setAvailable(false);

                List<Reservation> conflictingReservations = reservationRepository.findByRoomAndDatesOverlap(
                        room.getId(), reservation.getCheckInDate(), reservation.getCheckOutDate());

                for (Reservation conflictingReservation : conflictingReservations) {
                    if (!conflictingReservation.getId().equals(reservation.getId())) {
                        conflictingReservation.setStatus(ReservationStatus.CANCELLED);
                        reservationRepository.save(conflictingReservation);
                    }
                }
            } else {
                reservation.setStatus(ReservationStatus.REJECTED);
                room.setAvailable(true);
            }

            reservationRepository.save(reservation);
            roomRepository.save(room);

            return true;
        }
        return false;
    }


}
