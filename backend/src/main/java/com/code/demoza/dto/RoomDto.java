package com.code.demoza.dto;



import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RoomDto {

    private Long id;

    private String name;

    private String type;
    private Long price;

    private boolean available;

}
