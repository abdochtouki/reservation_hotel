package com.code.demoza.dto;

import lombok.Data;

@Data
public class AuthentificationRequest {
    private String email;
    private String password;
}
