package com.practice.khstudy.repository;

import com.practice.khstudy.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
