package it.vincenzopicone.foodball.auth.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vincenzopicone.foodball.auth.entity.User;
import it.vincenzopicone.foodball.auth.payload.JWTAuthResponse;
import it.vincenzopicone.foodball.auth.payload.LoginDto;
import it.vincenzopicone.foodball.auth.payload.ModifyUserDto;
import it.vincenzopicone.foodball.auth.payload.RegisterDto;
import it.vincenzopicone.foodball.auth.repository.RoleRepository;
import it.vincenzopicone.foodball.auth.repository.UserRepository;
import it.vincenzopicone.foodball.auth.service.AuthService;
import it.vincenzopicone.foodball.model.Locale;
import it.vincenzopicone.foodball.model.TipoLocale;
import it.vincenzopicone.foodball.payload.CreaNuovoLocaleDto;
import it.vincenzopicone.foodball.service.LocaleService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private AuthService authService;
    private PasswordEncoder passwordEncoder;
    @Autowired LocaleService localeService;
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
        jwtAuthResponse.setPrenotazione(U.getPrenotazioni());
        jwtAuthResponse.setName(U.getName());
        jwtAuthResponse.setEmail(U.getEmail());
        jwtAuthResponse.setLocale(U.getLocale());
        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	@PostMapping("/register/locale")
	public ResponseEntity<?> createNewStore(@RequestBody CreaNuovoLocaleDto nuovoLocale) {
		User U = repo.findByUsername(nuovoLocale.getUsername()).get();
		Locale L = new Locale();
		L.setUser(U);
		L.setCitta(nuovoLocale.getCitta());
		L.setIndirizzo(nuovoLocale.getIndirizzolocale());
		L.setNomelocale(nuovoLocale.getNomelocale());
		if(nuovoLocale.getTipolocale().equals("RISTORANTE")) {
			L.setTipolocale(TipoLocale.RISTORANTE);
		} else if (nuovoLocale.getTipolocale().equals("PUB")) {
			L.setTipolocale(TipoLocale.PUB);
		} else if (nuovoLocale.getTipolocale().equals("PIZZERIA")) {
			L.setTipolocale(TipoLocale.PIZZERIA);		
		} else if (nuovoLocale.getTipolocale().equals("BURGER")) {
			L.setTipolocale(TipoLocale.BURGER);
		}
		U.setLocale(L);
//	    repo.save(U);
		return new ResponseEntity<>(localeService.creaLocale(L), HttpStatus.OK);
	}
    
    @GetMapping("/profilo/{username}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> viewProfile(@PathVariable String username) {
    	return new ResponseEntity<>(repo.findByUsername(username), HttpStatus.OK);
    }
    
    @PutMapping("/profilo/modifica")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> modifyProfile(@RequestBody ModifyUserDto modUser) {
    	User U = repo.findByUsername(modUser.getUsername()).get();
    	U.setEmail(modUser.getEmail());
    	U.setName(modUser.getName());
    	U.setIndirizzo(modUser.getIndirizzo());
    	U.setCitta(modUser.getCitta());
    	U.setNumerotelefono(modUser.getNumerotelefono());
    	repo.save(U);
    	return new ResponseEntity<>(HttpStatus.OK);
    	
    }

}
