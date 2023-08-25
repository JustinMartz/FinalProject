package com.skilldistillery.duality.services;

import java.util.List;

import com.skilldistillery.duality.entities.BehaviorReport;

public interface BehaviorReportService {

	

	BehaviorReport getById(int behaviorReportId);

	BehaviorReport create(BehaviorReport newBehaviorReport);

	BehaviorReport update(int behaviorReportId, BehaviorReport newBehaviorReport);

	boolean delete(int behaviorReportId);

	List<BehaviorReport> listAllBehaviorReports();



}
