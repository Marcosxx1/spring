package com.estudos.restfullwebservices.repository;

import com.estudos.restfullwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByDriverLicense(String driverLicense);
}
