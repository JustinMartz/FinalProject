package com.skilldistillery.duality.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.duality.entities.BehaviorReport;
import com.skilldistillery.duality.entities.User;
import com.skilldistillery.duality.repositories.BehaviorReportRepository;
import com.skilldistillery.duality.repositories.UserRepository;

@Service
public class BehaviorReportServiceImpl implements BehaviorReportService {

	
	@Autowired
	private BehaviorReportRepository behaviorReportRepo;
	
	@Autowired
	private UserRepository userRepo;

	
	
	@Override
	public List<BehaviorReport> listAllBehaviorReports() {
		return behaviorReportRepo.findAll();
	}

	@Override
	public BehaviorReport getById(int behaviorReportId) {
		Optional<BehaviorReport> behaviorReportOpt = behaviorReportRepo.findById(behaviorReportId);
		if (behaviorReportOpt.isPresent()) {
			return behaviorReportOpt.get();
		}
		return null;
	}

	@Override
	public BehaviorReport create(BehaviorReport newBehaviorReport, String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
		    newBehaviorReport.setUser(user);
		    if ( newBehaviorReport.getId() > 0) {
				return null;
			}
			if (newBehaviorReport.getBehavior() == null) {
				return null;
			}
		    return behaviorReportRepo.saveAndFlush(newBehaviorReport);
		  }
		
		return null;
	}

	@Override
	public BehaviorReport update(int behaviorReportId, BehaviorReport newBehaviorReport) {
		BehaviorReport existingBehaviorReport = null;
		Optional<BehaviorReport> existingOpt = behaviorReportRepo.findById(behaviorReportId);
		if (existingOpt.isPresent()) {
			existingBehaviorReport = existingOpt.get();
			existingBehaviorReport.setBehavior(newBehaviorReport.getBehavior());
			existingBehaviorReport.setIntensity(newBehaviorReport.getIntensity());
			existingBehaviorReport.setUser(newBehaviorReport.getUser());
			behaviorReportRepo.saveAndFlush(existingBehaviorReport);
		}
		return existingBehaviorReport;
	}

	@Override
	public boolean delete(int behaviorReportId) {
		boolean deleted = false;
		Optional<BehaviorReport> toDeleteOpt = behaviorReportRepo.findById(behaviorReportId);
		if (toDeleteOpt.isPresent()) {
			behaviorReportRepo.delete(toDeleteOpt.get());
			deleted = true;
		}

		return deleted;
	}


}
