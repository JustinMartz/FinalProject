package com.skilldistillery.duality.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.duality.entities.DirectMessage;

public interface DirectMessageRepository extends JpaRepository<DirectMessage, Integer> {



}
