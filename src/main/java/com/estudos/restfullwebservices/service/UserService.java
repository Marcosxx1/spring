package com.estudos.restfullwebservices.service;

import com.estudos.restfullwebservices.dtos.UserCreateDTO;
import com.estudos.restfullwebservices.exception.UserFoundException;
import com.estudos.restfullwebservices.exception.UserNotFoundException;
import com.estudos.restfullwebservices.model.User;
import com.estudos.restfullwebservices.repository.PostRepository;
import com.estudos.restfullwebservices.repository.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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

    public URI createUser(User user) {
        System.out.println("In the service: " + user);
        var existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) throw new UserFoundException("User already registered with this email");

        var existingUserByLicense = userRepository.findByDriverLicense(user.getDriverLicense());
        if (existingUserByLicense.isPresent())
            throw new UserFoundException("User already registered with this driver's license");

        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return location;
    }

    public User updateUser(Integer id, UserCreateDTO updatedUserDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        existingUser.setName(updatedUserDTO.getName());
        existingUser.setEmail(updatedUserDTO.getEmail());
        existingUser.setPassword(updatedUserDTO.getPassword());
        existingUser.setIsAdmin(updatedUserDTO.getIsAdmin());
        existingUser.setBirthDate(updatedUserDTO.getBirthDate());
        existingUser.setDriverLicense(updatedUserDTO.getDriverLicense());
        existingUser.setCep(updatedUserDTO.getCep());

        return userRepository.save(existingUser);
    }

    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    public EntityModel<User> getUserById( Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) throw new UserNotFoundException("id" + id);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    public void deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) throw new UserNotFoundException("id" + id);
        userRepository.deleteById(id);
    }


}
/*no instance(s) of type variable(s) T exist so that ResponseEntity<T> conforms to List<Post>
 * trocar o retorno para ResponseEntity<Object>*/