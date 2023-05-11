package it.vincenzopicone.foodball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vincenzopicone.foodball.auth.repository.UserRepository;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/profilo")
public class UserController {
	
//	@Autowired UserRepository repo;
//    
//    @GetMapping("/{id}")
//    @PreAuthorize("isAuthenticated()")
//	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
//		return new ResponseEntity <User>(repo.findById(id).get(), HttpStatus.OK);
//	}

}
