package it.vincenzopicone.foodball.auth.service;

import it.vincenzopicone.foodball.auth.payload.LoginDto;
import it.vincenzopicone.foodball.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
