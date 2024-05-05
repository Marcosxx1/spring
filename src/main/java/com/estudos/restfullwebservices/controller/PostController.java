package com.estudos.restfullwebservices.controller;

import com.estudos.restfullwebservices.exception.PostNotFoundException;
import com.estudos.restfullwebservices.exception.UserNotFoundException;
import com.estudos.restfullwebservices.model.Post;
import com.estudos.restfullwebservices.repository.PostRepository;
import com.estudos.restfullwebservices.repository.UserRepository;
import com.estudos.restfullwebservices.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final UserRepository userRepository;

    @Autowired
    public PostController(PostRepository postRepository, PostService postService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postService = postService;
        this.userRepository = userRepository;
    }

    @PostMapping("{user_id}/create")
    public ResponseEntity<Object> createPost(@PathVariable Integer user_id, @Valid @RequestBody Post post) {
        URI location = postService.createPostForUser(user_id, post);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{user_id}/get-by-user-id")
    public ResponseEntity<List<Post>> retrieveByUserId(@PathVariable Integer user_id) {
        List<Post> posts = postService.retrievePostForUser(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/jpa/user/{user_id}/posts-create/{post_id}")
    public ResponseEntity<Object> retrievePostById(@PathVariable Integer user_id, @PathVariable Integer post_id) {
        userRepository.findById(user_id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + user_id));

        Post existPost = postRepository.findById(post_id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + post_id));

        return ResponseEntity.ok(existPost);
    }
}
