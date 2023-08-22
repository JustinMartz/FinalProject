package com.skilldistillery.duality.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.duality.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
}
