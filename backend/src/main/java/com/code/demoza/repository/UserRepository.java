package com.code.demoza.repository;

import com.code.demoza.bean.User;
import com.code.demoza.bean.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserRole(UserRole userRole);
}

