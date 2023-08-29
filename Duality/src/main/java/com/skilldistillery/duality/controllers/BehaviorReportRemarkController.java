package com.skilldistillery.duality.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.duality.entities.BehaviorReport;
import com.skilldistillery.duality.entities.BehaviorReportRemark;
import com.skilldistillery.duality.services.BehaviorReportRemarkService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class BehaviorReportRemarkController {

	@Autowired
	private BehaviorReportRemarkService behaviorReportRemarkService;

	@GetMapping("behaviorReportRemarks")
	List<BehaviorReportRemark> listBehaviorReportRemark(HttpServletResponse res) {
		List<BehaviorReportRemark> behaviorReportRemarkReportRemarks = null;
		behaviorReportRemarkReportRemarks = behaviorReportRemarkService.listAllBehaviorReportRemarks();

		if (behaviorReportRemarkReportRemarks == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}

		return behaviorReportRemarkReportRemarks;
	}

	@GetMapping("behaviorReportRemarks/{id}")
	BehaviorReportRemark getBehaviorReportRemarkById(@PathVariable("id") int behaviorReportRemarkId,  HttpServletResponse res) {
		BehaviorReportRemark behaviorReportRemark = behaviorReportRemarkService.getById(behaviorReportRemarkId);
		if (behaviorReportRemark == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}

		return behaviorReportRemark;
	}

	@PostMapping("behaviorReportRemarks")
	public BehaviorReportRemark createBehaviorReportRemark(Principal principal, @RequestBody BehaviorReportRemark brr, HttpServletResponse res, HttpServletRequest req) {
		BehaviorReportRemark newBRR = behaviorReportRemarkService.create(principal.getName(), brr);
		if (newBRR != null) {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL().append("/" + newBRR.getId());
			res.setHeader("Location", url.toString());
		} else {
			res.setStatus(400);
		}
		return newBRR;
	}
	


}

