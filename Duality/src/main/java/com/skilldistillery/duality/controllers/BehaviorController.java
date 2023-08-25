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

import com.skilldistillery.duality.entities.Behavior;
import com.skilldistillery.duality.services.BehaviorService;
@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class BehaviorController {

	@Autowired
	private BehaviorService behaviorService;

	@GetMapping("behaviors")
	List<Behavior> listBehavior(Principal principal, HttpServletResponse res) {
		List<Behavior> behaviors= null;
		behaviors=behaviorService.listAllBehaviors();
		
		if (behaviors == null) {
			res.setStatus(404);
		}
		else {
			res.setStatus(200);
		}
		
		return behaviors;
	}

	@GetMapping("behavior/{id}")
	Behavior getBehaviorById(@PathVariable("id") int behaviorId, Principal principal, HttpServletResponse res) {
		Behavior behavior = behaviorService.getById(behaviorId);
		if (principal.getName()!=null) {
			if (behavior == null) {
				res.setStatus(404);
			}
		}
		return behavior;
	}

	@PostMapping("behavior")
	public Behavior createBehavior(@RequestBody Behavior behavior,Principal principal, HttpServletResponse res, HttpServletRequest req) {
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
	public Behavior updateBehavior(@PathVariable int behaviorId,Principal principal, @RequestBody Behavior behavior,
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
	public void deleteBehavior(@PathVariable("behaviorId") Integer behaviorId,Principal principal, HttpServletResponse res) {
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
