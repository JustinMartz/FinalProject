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

import com.skilldistillery.duality.entities.Behavior;
import com.skilldistillery.duality.services.BehaviorService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
public class BehaviorController {

	@Autowired
	private BehaviorService behaviorService;

	@GetMapping("behaviors")
	List<Behavior> listBehavior( HttpServletResponse res) {
		List<Behavior> behaviors = null;
		behaviors = behaviorService.listAllBehaviors();

		if (behaviors == null) {
			res.setStatus(404);
		}

		return behaviors;
	}

	@GetMapping("behaviors/{id}")
	Behavior getBehaviorById(@PathVariable("id") int behaviorId, Principal principal, HttpServletResponse res) {
		Behavior behavior = behaviorService.getById(behaviorId);
		if (principal.getName() != null) {
			if (behavior == null) {
				res.setStatus(404);
			}
		}
		return behavior;
	}

}
