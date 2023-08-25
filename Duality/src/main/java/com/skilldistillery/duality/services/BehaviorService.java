package com.skilldistillery.duality.services;

import java.util.List;

import com.skilldistillery.duality.entities.Behavior;

public interface BehaviorService {

	List<Behavior> listAllBehaviors();

	Behavior getById(int behaviorId);

	Behavior create(Behavior newBehavior);

	Behavior update(int behaviorId, Behavior newBehavior);

	boolean delete(int behaviorId);

}
