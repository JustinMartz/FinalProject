package com.skilldistillery.duality.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.duality.entities.Behavior;
import com.skilldistillery.duality.repositories.BehaviorRepository;

public class BehaviorServiceImpl implements BehaviorService {

	@Autowired
	private BehaviorRepository behaviorRepo;

	@Override
	public List<Behavior> listAllBehaviors() {
		return behaviorRepo.findAll();
	}

	@Override
	public Behavior getById(int behaviorId) {
		Optional<Behavior> behaviorOpt = behaviorRepo.findById(behaviorId);
		if (behaviorOpt.isPresent()) {
			return behaviorOpt.get();
		}
		return null;
	}

	@Override
	public Behavior create(Behavior newBehavior) {

		return behaviorRepo.saveAndFlush(newBehavior);
	}

	@Override
	public Behavior update(int behaviorId, Behavior newBehavior) {
		Behavior existingBehavior = null;
		Optional<Behavior> existingOpt = behaviorRepo.findById(behaviorId);
		if (existingOpt.isPresent()) {
			existingBehavior = existingOpt.get();
			existingBehavior.setBehaviorType(newBehavior.getBehaviorType());
			existingBehavior.setName(newBehavior.getName());
			existingBehavior.setSeverity(newBehavior.getSeverity());
			behaviorRepo.saveAndFlush(existingBehavior);
		}
		return existingBehavior;
	}

	@Override
	public boolean delete(int behaviorId) {
		boolean deleted = false;
		Optional<Behavior> toDeleteOpt = behaviorRepo.findById(behaviorId);
		if (toDeleteOpt.isPresent()) {
			behaviorRepo.delete(toDeleteOpt.get());
			deleted = true;
		}

		return deleted;
	}
}
