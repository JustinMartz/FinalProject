package com.skilldistillery.duality.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.duality.entities.BehaviorReport;
import com.skilldistillery.duality.services.BehaviorReportService;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class BehaviorReportController {
	
	@Autowired
	private BehaviorReportService behaviorReportService;

	@GetMapping("behaviorReports")
	List<BehaviorReport> listBehaviorReport() {
		return behaviorReportService.listAllBehaviorReports();
	}

	@GetMapping("behaviorReports/{id}")
	BehaviorReport getBehaviorReportById(@PathVariable("id") Integer behaviorReportId, HttpServletResponse res) {
		BehaviorReport behaviorReport = behaviorReportService.getById(behaviorReportId);
		if (behaviorReport == null) {
			res.setStatus(404);
		}
		return behaviorReport;
	}
	
	@PostMapping("behaviorReports/month")
	List<Boolean> getReportsForMonth(Principal principal, @RequestBody String isodate, HttpServletResponse response) {
		List<Boolean> reports = behaviorReportService.getReportBooleansForMonth(principal.getName(), isodate);
		if (reports == null) {
			response.setStatus(404);
		} else {
			response.setStatus(200);
		}
		return reports;
	}
	
	@PostMapping("behaviorReports/monthReport")
	List<BehaviorReport> getAllReportsForMonth(Principal principal, @RequestBody String isodate, HttpServletResponse response) {
		System.out.println("*** getAllReportsForMonth controller");
		List<BehaviorReport> reports = behaviorReportService.getReportsForMonth(principal.getName(), isodate);
		if (reports == null) {
			response.setStatus(404);
		} else {
			response.setStatus(200);
		}
		return reports;
	}
	
	@PostMapping("behaviorReports/day")
	List<BehaviorReport> getReportsForDay(Principal principal, @RequestBody String isodate, HttpServletResponse response) {
		List<BehaviorReport> reports = behaviorReportService.getReportsForDay(principal.getName(), isodate);
		if (reports == null) {
			response.setStatus(404);
		} else {
			response.setStatus(200);
		}
		return reports;
	}
	

	@PostMapping("behaviorReports")
	public BehaviorReport createBehaviorReport(Principal principal, @RequestBody BehaviorReport behaviorReport, HttpServletResponse res, HttpServletRequest req) {
		BehaviorReport newBR = behaviorReportService.create(behaviorReport, principal.getName());
		if (newBR != null) {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL().append("/" + newBR.getId());
			res.setHeader("Location", url.toString());
		} else {
			res.setStatus(400);
		}
		return newBR;
	}

	@PutMapping("behaviorReports/{behaviorReportId}")
	public BehaviorReport updateBehaviorReport(@PathVariable int behaviorReportId, @RequestBody BehaviorReport behaviorReport,
			HttpServletResponse res) {
		try {
			behaviorReport = behaviorReportService.update(behaviorReportId, behaviorReport);
			if (behaviorReport == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return behaviorReport;
	}

	@DeleteMapping("behaviorReports/{behaviorReportId}")
	public void deleteBehaviorReport(@PathVariable("behaviorReportId") Integer behaviorReportId, HttpServletResponse res) {
		try {
			if (behaviorReportService.delete(behaviorReportId)) {
				res.setStatus(200);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
}
