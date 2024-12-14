package com.code.demoza.dto;

import com.code.demoza.bean.enums.UserRole;
import lombok.Data;

@Data
public class AuthentificationResponce {
    private String jwt;
    private Long userId;
    private UserRole userrole;
}
