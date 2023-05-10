package it.vincenzopicone.foodball.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/auth")
	@PreAuthorize("isAuthenticated()")
	public String autenticatedAccess() {
		return "Autenticated Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String userAccess() {
		return "User Content.";
	}


	@GetMapping("/restaurant")
	@PreAuthorize("hasRole('RESTAURANT')")
	public String restaurantAccess() {
		return "Restaurant Board.";
	}
}
