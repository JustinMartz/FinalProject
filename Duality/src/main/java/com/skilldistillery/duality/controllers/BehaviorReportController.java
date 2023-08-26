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
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.duality.entities.BehaviorReport;
import com.skilldistillery.duality.services.BehaviorReportService;

@RestController
@CrossOrigin({"*", "http://localhost/"})
public class BehaviorReportController {
	
	@Autowired
	private BehaviorReportService behaviorReportService;

	@GetMapping("behaviorReport")
	List<BehaviorReport> listBehaviorReport() {
		return behaviorReportService.listAllBehaviorReports();
	}

	@GetMapping("behaviorReport/{id}")
	BehaviorReport getBehaviorReportById(@PathVariable("id") Integer behaviorReportId, HttpServletResponse res) {
		BehaviorReport behaviorReport = behaviorReportService.getById(behaviorReportId);
		if (behaviorReport == null) {
			res.setStatus(404);
		}
		return behaviorReport;
	}

	@PostMapping("behaviorReport")
	public BehaviorReport createBehaviorReport(Principal principal, @RequestBody BehaviorReport behaviorReport, HttpServletResponse res, HttpServletRequest req) {
		System.out.println(behaviorReport);
		try {
			behaviorReport = behaviorReportService.create(behaviorReport);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(behaviorReport.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			behaviorReport = null;
		}
		return behaviorReport;
	}

	@PutMapping("behaviorReport/{behaviorReportId}")
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

	@DeleteMapping("behaviorReport/{behaviorReportId}")
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
