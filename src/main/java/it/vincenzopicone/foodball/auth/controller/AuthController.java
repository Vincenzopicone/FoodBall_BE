package it.vincenzopicone.foodball.auth.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vincenzopicone.foodball.auth.entity.User;
import it.vincenzopicone.foodball.auth.payload.JWTAuthResponse;
import it.vincenzopicone.foodball.auth.payload.LoginDto;
import it.vincenzopicone.foodball.auth.payload.RegisterDto;
import it.vincenzopicone.foodball.auth.repository.RoleRepository;
import it.vincenzopicone.foodball.auth.repository.UserRepository;
import it.vincenzopicone.foodball.auth.service.AuthService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private AuthService authService;
    @Autowired UserRepository repo;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
           	
    	String token = authService.login(loginDto);
    	User U = repo.findByUsername(loginDto.getUsername()).get();

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setUsername(loginDto.getUsername());
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setRoles(U.getRoles());

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    

}
