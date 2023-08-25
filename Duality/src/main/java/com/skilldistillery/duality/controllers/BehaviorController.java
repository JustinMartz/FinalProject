package com.skilldistillery.duality.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.skilldistillery.duality.entities.Behavior;
import com.skilldistillery.duality.services.BehaviorService;

public class BehaviorController {

	@Autowired
	private BehaviorService behaviorService;

	@GetMapping("behavior")
	List<Behavior> listBehavior() {
		return behaviorService.listAllBehaviors();
	}

	@GetMapping("behavior/{id}")
	Behavior getBehaviorById(@PathVariable("id") Integer behaviorId, HttpServletResponse res) {
		Behavior behavior = behaviorService.getById(behaviorId);
		if (behavior == null) {
			res.setStatus(404);
		}
		return behavior;
	}

	@PostMapping("behavior")
	public Behavior createBehavior(@RequestBody Behavior behavior, HttpServletResponse res, HttpServletRequest req) {
		System.out.println(behavior);
		try {
			behavior = behaviorService.create(behavior);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(behavior.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			behavior = null;
		}
		return behavior;
	}

	@PutMapping("behavior/{behaviorId}")
	public Behavior updateBehavior(@PathVariable int behaviorId, @RequestBody Behavior behavior,
			HttpServletResponse res) {
		try {
			behavior = behaviorService.update(behaviorId, behavior);
			if (behavior == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return behavior;
	}

	@DeleteMapping("behavior/{behaviorId}")
	public void deleteBehavior(@PathVariable("behaviorId") Integer behaviorId, HttpServletResponse res) {
		try {
			if (behaviorService.delete(behaviorId)) {
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
