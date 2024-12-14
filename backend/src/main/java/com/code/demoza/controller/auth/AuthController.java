package com.code.demoza.controller.auth;

import com.code.demoza.bean.User;
import com.code.demoza.dto.AuthentificationRequest;
import com.code.demoza.dto.AuthentificationResponce;
import com.code.demoza.dto.SignupRequest;
import com.code.demoza.dto.UserDto;
import com.code.demoza.repository.UserRepository;
import com.code.demoza.services.jwt.UserService1;
import com.code.demoza.services.auth.UserService;
import com.code.demoza.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService1 userService1;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        try {
            UserDto createdUser = userService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>("User not created,come again later", HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/login")
    public AuthentificationResponce createAuthentificationResponce(@RequestBody AuthentificationRequest authentificationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationRequest.getEmail(), authentificationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
        final UserDetails userDetails = userService1.getUserDetailsService().loadUserByUsername(authentificationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthentificationResponce authentificationResponce = new AuthentificationResponce();
        if (optionalUser.isPresent()) {
            authentificationResponce.setJwt(jwt);
            authentificationResponce.setUserrole(optionalUser.get().getUserRole());
            authentificationResponce.setUserId(optionalUser.get().getId());

        }
        return authentificationResponce;
    }

}
