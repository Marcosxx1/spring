package com.estudos.restfullwebservices.repository;

import com.estudos.restfullwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
