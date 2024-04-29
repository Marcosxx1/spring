package com.estudos.restfullwebservices.repository;

import com.estudos.restfullwebservices.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
