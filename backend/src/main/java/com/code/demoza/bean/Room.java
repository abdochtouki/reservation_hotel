package com.code.demoza.bean;

import com.code.demoza.dto.RoomDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;
    private Long price;
    private boolean available;

    public RoomDto getRoomDto(){
        RoomDto roomDto = new RoomDto();
        roomDto.setId(id);
        roomDto.setName(name);
        roomDto.setType(type);
        roomDto.setPrice(price);
        roomDto.setAvailable(available);
        return roomDto;
    }

}
