package com.skilldistillery.duality.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.duality.entities.Behavior;

public interface BehaviorRepository extends JpaRepository<Behavior, Integer> {

}
