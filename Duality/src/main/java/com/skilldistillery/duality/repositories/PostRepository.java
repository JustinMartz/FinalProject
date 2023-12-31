package com.skilldistillery.duality.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.duality.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
