package com.example.aworldtoyou.repository;

import com.example.aworldtoyou.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
