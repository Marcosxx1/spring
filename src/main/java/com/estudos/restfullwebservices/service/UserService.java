package com.estudos.restfullwebservices.service;

import com.estudos.restfullwebservices.exception.UserNotFoundException;
import com.estudos.restfullwebservices.model.Post;
import com.estudos.restfullwebservices.model.User;
import com.estudos.restfullwebservices.repository.PostRepository;
import com.estudos.restfullwebservices.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping("/jpa/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        System.out.println(user);
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/jpa/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User existingUser = userRepository
                .findById(id).
                orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id));

        existingUser.setName(updatedUser.getName());
        existingUser.setBirthDate(updatedUser.getBirthDate());

        userRepository.save(existingUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/jpa/user/get-all")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/user/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) throw new UserNotFoundException("id" + id);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @DeleteMapping("/jpa/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }


}
/*no instance(s) of type variable(s) T exist so that ResponseEntity<T> conforms to List<Post>
 * trocar o retorno para ResponseEntity<Object>*/