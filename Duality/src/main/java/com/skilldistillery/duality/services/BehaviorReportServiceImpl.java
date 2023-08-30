package com.skilldistillery.duality.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
			if (newBehaviorReport.getId() > 0) {
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

	@Override
	public List<BehaviorReport> getAllReportsByDate(LocalDateTime time) {
		System.out.println("*** getAllReportsByDate() ***");
		System.out.println("time: " + time);
		List<BehaviorReport> reports = behaviorReportRepo.findByCreateDate(time);
		for (BehaviorReport r : reports) {
			System.out.println(r);
		}
		return null;
	}


	@Override
	public List<BehaviorReport> getReportsForDay(String username, String isodate) {
		String[] isoparts = isodate.split("Z");
		int day = LocalDateTime.parse(isoparts[0]).getDayOfMonth();
		System.out.println("*** getReportsForDay() ***");
		System.out.println(isodate);
		User user = userRepo.findByUsername(username);

		String[] parts = isodate.split("-");
		String ftime = parts[0] + "-" + parts[1] + "-" + String.format("%02d", day) + "T00:00";
		System.out.println("ftime: " + ftime);
		String ltime = parts[0] + "-" + parts[1] + "-" + String.format("%02d", day) + "T23:59";
		System.out.println("ltime: " + ltime);

		LocalDateTime first = LocalDateTime.parse(ftime);
		LocalDateTime last = LocalDateTime.parse(ltime);
		System.out.println(first);
		System.out.println(last);

		List<BehaviorReport> reports = behaviorReportRepo.findByUserAndCreateDateBetweenOrderByCreateDateDesc(user,
				first, last);
		List<BehaviorReport> filtered = new ArrayList<BehaviorReport>();
//		int behavior = 1;
//		for (BehaviorReport r : reports) {
//
//			if (behavior == r.getBehavior().getId()) {
//				filtered.add(r);
//				behavior++;
//			}
//		}
		
		for (int behavior = 1; behavior < 21; behavior++) {
			System.out.println("behavior: " + behavior);
			for (BehaviorReport br : reports) {
				System.out.println("behavior report: " + br.getId());
				if (br.getBehavior().getId() == behavior) {
					filtered.add(br);
					System.out.println("Adding " + br.getBehavior());
					break;
				}
			}
		}
		return filtered;
	}

	@Override
	public List<BehaviorReport> getReportsForMonth(String username, String isodate) {
		System.out.println("*** getReportsForMonth()");
		String[] isoparts = isodate.split("Z");
		System.out.println(isoparts[0]);
		int month = LocalDateTime.parse(isoparts[0]).getMonthValue();

		User user = userRepo.findByUsername(username);

		String[] parts = isoparts[0].split("-");
		String ftime = parts[0] + "-" + String.format("%02d", month) + "-01T00:00:00.000";
		System.out.println("ftime: " + ftime);
		month++;
		String ltime = parts[0] + "-" + String.format("%02d", month) + "-01T00:00:00.000";
		System.out.println("ltime: " + ltime);

		LocalDateTime first = LocalDateTime.parse(ftime);
		LocalDateTime last = LocalDateTime.parse(ltime);
		System.out.println(first);
		System.out.println(last);

		List<BehaviorReport> reports = behaviorReportRepo.findByUserAndCreateDateBetweenOrderByCreateDateDesc(user,
				first, last);
		List<BehaviorReport> filtered = new ArrayList<BehaviorReport>();
//		int behavior = 1;
//		for (BehaviorReport r : reports) {
//			System.out.print("report: " + r.getId() + ", behavior: " + behavior);
//			if (behavior == r.getBehavior().getId()) {
//				filtered.add(r);
//				System.out.println(" - adding: " + r.getBehavior().getName());
//				behavior = r.getBehavior().getId() + 1;
//			} else
//			behavior++;
//			System.out.println("incremented behavior to " + behavior);
//		}
		
		for (int behavior = 1; behavior < 21; behavior++) {
			System.out.println("behavior: " + behavior);
			for (BehaviorReport br : reports) {
				System.out.println("behavior report: " + br.getId());
				if (br.getBehavior().getId() == behavior) {
					filtered.add(br);
					System.out.println("Adding " + br.getBehavior());
					break;
				}
			}
		}

		return filtered;
	}

	@Override
	public List<Boolean> getReportBooleansForMonth(String username, String isodate) {
		User user = userRepo.findByUsername(username);
		// if month is 30 days, set last's day to 30
		// if month is 31 days, set last's day to 31
		// if month is february, set last's day to 28
		// call repo method and get list of reports
		// fill array with either true or false if report exists for day
		// return array
		List<BehaviorReport> reports;
		List<Boolean> boolList = null;
		String[] parts = isodate.split("-");
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[0]);
		String fIsoDate = "";
		String lIsoDate = "";

		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			// for each
			boolList = new ArrayList<Boolean>();
			for (int i = 0; i < 31; i++) {
				boolList.add(false);
			}
			break;
		case 2:
			//
			boolList = new ArrayList<Boolean>();
			for (int i = 0; i < 28; i++) {
				boolList.add(false);
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			boolList = new ArrayList<Boolean>();
			for (int i = 0; i < 30; i++) {
				boolList.add(false);
			}
			// for each
			break;
		default:
			System.err.print("BehaviorReportServiceImpl.getReportBooleansForMonth() - Month error: " + month);
		}

		System.out.println("int month: " + month);
		if (month > 11) {
			year++;
			month = 1;
			fIsoDate = parts[0] + "-" + parts[1] + "-01T00:00:00.000";
			lIsoDate = year + "-" + String.format("%02d", month) + "-01T00:00:00.000";

		} else {
			month++;
			fIsoDate = parts[0] + "-" + parts[1] + "-01T00:00:00.000";
			lIsoDate = parts[0] + "-" + String.format("%02d", month) + "-01T00:00:00.000";
		}

		// 2014-04-28T16:00:49.455
		System.out.println("*** isodate: " + isodate);
		System.out.println("*** month: " + parts[1]);
		System.out.println("*** fIsodate: " + fIsoDate);
		System.out.println("*** lIsodate: " + lIsoDate);

		// get BRs for date range
		LocalDateTime first = LocalDateTime.parse(fIsoDate);
		LocalDateTime last = LocalDateTime.parse(lIsoDate);

		reports = behaviorReportRepo.findByUserAndCreateDateBetween(user, first, last);
		System.out.println("*** boolList size: " + boolList.size());

		outer: for (int i = 0; i < boolList.size(); i++) {
			for (BehaviorReport r : reports) {
				if ((r.getCreateDate().getDayOfMonth() == i + 1) && (boolList.get(i) != true)) {
					System.out.println(r.getCreateDate().getDayOfMonth() + " matches " + (i + 1));
					boolList.set(i, true);
					break;
				}
			}
		}

//		for (int i = 0; i < reports.size(); i++) {
//			for (Boolean b : boolList) {
//				if (reports.get(i).getCreateDate().getDayOfMonth() == i + 1 && b != true) {
//					boolList.add(i, true);
//				}
//			}
//		}

		int i = 0;
		for (Boolean b : boolList) {

			System.out.println("boolList[" + i + "]" + ": " + b);
			i++;
		}

		System.out.println("*** first: " + first);
		System.out.println("*** last : " + last);

//		switch (month) {
//		case 0:
//			
//		}
//		List<BehaviorReport> reports = behaviorReportRepo.findByByCreateDateBetween(null, null);
		return boolList;
	}


}
