package com.estudos.restfullwebservices.model;

import com.estudos.restfullwebservices.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {

    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }


    public EntityModel<User> getUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);

        if (user == null) throw new UserNotFoundException("id" + id);

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    public void deleteUser(@PathVariable int id) {
        userDaoService.deleteById(id);
    }


    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        userDaoService.update(user);

        return ResponseEntity.ok().build();
    }
}
