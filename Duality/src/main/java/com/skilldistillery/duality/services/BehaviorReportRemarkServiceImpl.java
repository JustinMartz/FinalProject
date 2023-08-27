package com.skilldistillery.duality.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.duality.entities.BehaviorReportRemark;
import com.skilldistillery.duality.repositories.BehaviorReportRemarkRepository;

@Service
public class BehaviorReportRemarkServiceImpl implements BehaviorReportRemarkService {

	@Autowired
	private BehaviorReportRemarkRepository behaviorRepo;
	
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

}
