package com.estudos.restfullwebservices.controller;

import com.estudos.restfullwebservices.dtos.UserCreateDTO;
import com.estudos.restfullwebservices.exception.UserNotFoundException;
import com.estudos.restfullwebservices.model.User;
import com.estudos.restfullwebservices.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        BeanUtils.copyProperties(user, userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userModel));
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @Valid @RequestBody UserCreateDTO user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> retrieveAllUsers() {
        List<User> users = userService.retrieveAllUsers();

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
            // return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Integer id) {
        try {
            EntityModel<User> entityModel = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(entityModel);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();

    }

}
