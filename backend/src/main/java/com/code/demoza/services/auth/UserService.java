package com.code.demoza.services.auth;

import com.code.demoza.dto.SignupRequest;
import com.code.demoza.dto.UserDto;

public interface UserService {
    UserDto  createUser(SignupRequest signupRequest);
}
