package com.estudos.restfullwebservices.service;

import com.estudos.restfullwebservices.exception.PostNotFoundException;
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
public class PostService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping("/jpa/user/{id}/posts-create")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) throw new UserNotFoundException("id: " + id);

        post.setUser(user.get());// quando formos setar de um Optional<>, devemos usar o .get()

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/user/{id}/posts")
    public List<Post> retrievePostForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) throw new UserNotFoundException("id: " + id);

        return user.get().getPosts();
    }

    @GetMapping("/jpa/user/{user_id}/posts-create/{post_id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer user_id, @PathVariable Integer post_id) {
        System.out.println("user_id " + user_id);
        System.out.println("post_id " + post_id);
        User existingUser = userRepository
                .findById(user_id).
                orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + user_id));

        Post existPost = postRepository
                .findById(post_id)
                .orElseThrow(() ->
                        new PostNotFoundException("Post not found with id:" + post_id));

        return ResponseEntity.ok(existPost);
    }


}
/*no instance(s) of type variable(s) T exist so that ResponseEntity<T> conforms to List<Post>
 * trocar o retorno para ResponseEntity<Object>*/