package com.skilldistillery.duality.services;

import java.time.LocalDateTime;
import java.util.List;

import com.skilldistillery.duality.entities.BehaviorReport;

public interface BehaviorReportService {

	

	BehaviorReport getById(int behaviorReportId);

	BehaviorReport create(BehaviorReport newBehaviorReport, String username);

	BehaviorReport update(int behaviorReportId, BehaviorReport newBehaviorReport);

	boolean delete(int behaviorReportId);

	List<BehaviorReport> listAllBehaviorReports();

	List<BehaviorReport> getAllReportsByDate(LocalDateTime time);

	List<Boolean> getReportBooleansForMonth(String username, String isodate);

	List<BehaviorReport> getReportsForDay(String username, String isodate);

	List<BehaviorReport> getReportsForMonth(String username, String isodate);

}
