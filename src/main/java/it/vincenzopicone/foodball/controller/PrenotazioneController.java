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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.model.Prenotazione;
import it.vincenzopicone.foodball.service.PartitaService;
import it.vincenzopicone.foodball.service.PrenotazioneService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/prenotazione")
public class PrenotazioneController {

	@Autowired PrenotazioneService prenotazioneService;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<List<Prenotazione>>(prenotazioneService.getAll(), HttpStatus.OK);
	}
	@GetMapping("/id/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(prenotazioneService.getPrenotazione(id), HttpStatus.OK);
	}
	@PostMapping("/crea")
//	@PreAuthorize("isAuthenticated()")
//	public ResponseEntity<?> newPrenotazione(Prenotazione prenotazione) {
//		return new ResponseEntity<>(prenotazioneService.creaPrenotazione(prenotazione.getLocale(), prenotazione.getEvento(), prenotazione.getUtente(), prenotazione.getData()), HttpStatus.OK);
//	}
	@GetMapping("/data/{data}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getclientiPerNumero(@PathVariable LocalDate data){
		return new ResponseEntity<List<Prenotazione>>(prenotazioneService.getPrenotazionePerData(data), HttpStatus.OK);
	}
	@GetMapping("/pageable")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Prenotazione>> getAllPage(Pageable pag) {
		return new ResponseEntity<Page<Prenotazione>>(prenotazioneService.getAllPrenotazionePageable(pag), HttpStatus.OK);
	}

}
