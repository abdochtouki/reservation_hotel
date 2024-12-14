package com.code.demoza.services.auth;

import com.code.demoza.bean.User;
import com.code.demoza.bean.enums.UserRole;
import com.code.demoza.dto.SignupRequest;
import com.code.demoza.dto.UserDto;
import com.code.demoza.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @PostConstruct
    public void createAdminAccount() {
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);

        if (adminAccount.isEmpty()) {
            User user = new User();
            user.setEmail("abdo@gmail.com");
            user.setName("abdo chtouki");
            user.setUserRole(UserRole.ADMIN);

            user.setPassword(new BCryptPasswordEncoder().encode("1999"));

            userRepository.save(user);

            System.out.println("Admin account created successfully");
        } else {
            System.out.println("Admin account already exists");
        }
    }
    public UserDto createUser(SignupRequest signupRequest) {
        if(userRepository.findByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User already exists");
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }



}

