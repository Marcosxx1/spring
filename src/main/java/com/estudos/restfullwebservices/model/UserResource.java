package com.estudos.restfullwebservices.model;

import com.estudos.restfullwebservices.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/user/get-all")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id" + id);
        }

        return user;
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userDaoService.deleteById(id);
    }


    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        userDaoService.update(user);

        return ResponseEntity.ok().build();
    }
}
