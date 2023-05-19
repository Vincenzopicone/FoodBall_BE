package it.vincenzopicone.foodball.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import it.vincenzopicone.foodball.auth.repository.UserRepository;
import it.vincenzopicone.foodball.model.Locale;
import it.vincenzopicone.foodball.model.TipoLocale;
import it.vincenzopicone.foodball.payload.CreaNuovoLocaleDto;
import it.vincenzopicone.foodball.service.LocaleService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/locale")
public class LocaleController {
	
	@Autowired LocaleService localeService;
	@Autowired UserRepository userRepo;
	
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<List<Locale>>(localeService.getAllLocale(), HttpStatus.OK);
	}
	@GetMapping("/id/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(localeService.getLocale(id), HttpStatus.OK);
	}
	@GetMapping("/pageable")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Locale>> getAllPage(Pageable pag) {
		return new ResponseEntity<Page<Locale>>(localeService.getAllLocalePageable(pag), HttpStatus.OK);
	}
	
	@GetMapping("/nome/{nome}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getLocalePerNomeParte(@PathVariable("nome") String nome){
		return new ResponseEntity<>(localeService.getAllLocaleByName(nome), HttpStatus.OK);
	}
	


}
