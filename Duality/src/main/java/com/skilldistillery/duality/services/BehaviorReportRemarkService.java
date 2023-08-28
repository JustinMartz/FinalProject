package com.skilldistillery.duality.services;

import java.util.List;

import com.skilldistillery.duality.entities.BehaviorReportRemark;

public interface BehaviorReportRemarkService {

	List<BehaviorReportRemark> listAllBehaviorReportRemarks();

	BehaviorReportRemark getById(int behaviorReportRemarkId);
	
	BehaviorReportRemark create(String username, BehaviorReportRemark newBRR);

}
