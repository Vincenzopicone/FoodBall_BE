package it.vincenzopicone.foodball.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vincenzopicone.foodball.auth.entity.User;
import it.vincenzopicone.foodball.auth.repository.UserRepository;
import it.vincenzopicone.foodball.model.Evento;
import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.model.Prenotazione;
import it.vincenzopicone.foodball.payload.CreaPrenotazioneDto;
import it.vincenzopicone.foodball.repository.EventoRepository;
import it.vincenzopicone.foodball.repository.PrenotazioneRepository;
import it.vincenzopicone.foodball.service.EventoService;
import it.vincenzopicone.foodball.service.PartitaService;
import it.vincenzopicone.foodball.service.PrenotazioneService;
import jakarta.persistence.EntityNotFoundException;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/prenotazione")
public class PrenotazioneController {

	@Autowired PrenotazioneService prenotazioneService;
	@Autowired PrenotazioneRepository repo;
	@Autowired EventoRepository eventoRepo;
	@Autowired EventoService eventoService;
	@Autowired UserRepository utenteRepo;
	
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
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> newPrenotazione(@RequestBody CreaPrenotazioneDto prenotazione) {
		Evento E = eventoRepo.findById(prenotazione.getIdevento()).get();
		User U = utenteRepo.findByUsername(prenotazione.getUsernameutente()).get();
		Prenotazione P = new Prenotazione();
		
		if(E.getPostidisponibili() >= prenotazione.getNumeropersone()) {
		E.setPostidisponibili(E.getPostidisponibili() - prenotazione.getNumeropersone());
		eventoService.updateEvento(E);
		P.setDataprenotazione(LocalDate.now());
		P.setDataevento(E.getData());
		P.setUtente(U);
		P.setNumeropersone(prenotazione.getNumeropersone());
		P.setEvento(E);
		return new ResponseEntity<Prenotazione>(prenotazioneService.creaPrenotazione(P), HttpStatus.OK);
		} else {
		
		}
		return new ResponseEntity<>(new Exception("Non ci sono posti disponibili"), HttpStatus.NOT_FOUND);
	}
	@GetMapping("/dataprenotazione/{data}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getPrenotazioniPerData(@PathVariable LocalDate data){
		return new ResponseEntity<List<Prenotazione>>(prenotazioneService.getPrenotazionePerDataprenotazione(data), HttpStatus.OK);
	}
	@GetMapping("/dataevento/{data}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getPrenotazioniPerDataEvento(@PathVariable LocalDate data){
		return new ResponseEntity<List<Prenotazione>>(prenotazioneService.getPrenotazionePerDataevento(data), HttpStatus.OK);
	}
	@GetMapping("/pageable")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Prenotazione>> getAllPage(Pageable pag) {
		return new ResponseEntity<Page<Prenotazione>>(prenotazioneService.getAllPrenotazionePageable(pag), HttpStatus.OK);
	}
	
	@DeleteMapping("/rimuovi/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> rimuoviPrenotazione(@PathVariable Long id) {
		return new ResponseEntity<>(prenotazioneService.removePrenotazione(id), HttpStatus.OK);
	}
	

}
