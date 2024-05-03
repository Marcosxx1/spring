package com.estudos.restfullwebservices.controller;

import com.estudos.restfullwebservices.dtos.UserCreateDTO;
import com.estudos.restfullwebservices.model.User;
import com.estudos.restfullwebservices.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreateDTO user) {
        User userModel = new User();
        System.out.println("user : " + user);

        BeanUtils.copyProperties(user, userModel);
        System.out.println("userModel : " + userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userModel));
    }

}
