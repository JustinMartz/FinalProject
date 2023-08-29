package com.skilldistillery.duality.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.duality.entities.BehaviorReportRemark;
import com.skilldistillery.duality.entities.User;
import com.skilldistillery.duality.repositories.BehaviorReportRemarkRepository;
import com.skilldistillery.duality.repositories.UserRepository;

@Service
public class BehaviorReportRemarkServiceImpl implements BehaviorReportRemarkService {

	@Autowired
	private BehaviorReportRemarkRepository behaviorRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<BehaviorReportRemark> listAllBehaviorReportRemarks() {
		return behaviorRepo.findAll();
	}

	@Override
	public BehaviorReportRemark getById(int behaviorId) {
		Optional<BehaviorReportRemark> behaviorOpt = behaviorRepo.findById(behaviorId);
		if (behaviorOpt.isPresent()) {
			return behaviorOpt.get();
		}
		return null;
	}
	
	@Override
	public BehaviorReportRemark create(String username, BehaviorReportRemark newBRR) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			newBRR.setUser(user);
			return behaviorRepo.saveAndFlush(newBRR);
			
		}
		return null;
	}

}
