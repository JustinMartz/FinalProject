package com.skilldistillery.duality.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.duality.entities.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
	
	public List<Resource> findByCreator_Id(Integer creatorId);
		

}
