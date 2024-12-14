package com.code.demoza.bean;

import com.code.demoza.bean.enums.ReservationStatus;
import com.code.demoza.dto.ReservationDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Long price;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "room_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;



    public ReservationDto reservationdto() {
        ReservationDto dto = new ReservationDto();
        dto.setId(id);
        dto.setCheckInDate(checkInDate);
        dto.setCheckOutDate(checkOutDate);
        dto.setPrice(price);
        dto.setStatus(status);

        dto.setUserId(user.getId());
        dto.setUserName(user.getName());

        dto.setRoomId(room.getId());
        dto.setRoomName(room.getName());
        dto.setRoomType(room.getType());

        return dto;
    }
}
