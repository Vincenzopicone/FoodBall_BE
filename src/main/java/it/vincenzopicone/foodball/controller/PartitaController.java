package it.vincenzopicone.foodball.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.service.PartitaService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/partite")
public class PartitaController {
	
	@Autowired PartitaService partitaService;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<List<Partita>>(partitaService.getAllPartite(), HttpStatus.OK);
	}
	@GetMapping("/id/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(partitaService.getPartita(id), HttpStatus.OK);
	}
	
	@GetMapping("/data/{data}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getclientiPerNumero(@PathVariable LocalDate data){
		return new ResponseEntity<List<Partita>>(partitaService.getPartitaPerData(data), HttpStatus.OK);
	}
	@GetMapping("/pageable")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Partita>> getAllPage(Pageable pag) {
		return new ResponseEntity<Page<Partita>>(partitaService.getAllPartitaPageable(pag), HttpStatus.OK);
	}
	
//	@GetMapping("/nome/{nome}")
//	@PreAuthorize("isAuthenticated()")
//	public ResponseEntity<?> getclientiPerNomeParte(@PathVariable("nome") String nome){
//		return new ResponseEntity<>(partitaService.getAllPartitaByName(nome), HttpStatus.OK);
//	}

}
