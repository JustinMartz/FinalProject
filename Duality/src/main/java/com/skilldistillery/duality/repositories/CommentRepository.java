package com.skilldistillery.duality.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.duality.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
