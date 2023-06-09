package it.vincenzopicone.foodball.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import it.vincenzopicone.foodball.faketeam.Squadra;
import it.vincenzopicone.foodball.model.Evento;
import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.model.TipoLocale;
import it.vincenzopicone.foodball.payload.CreaNuovoEventoDto;
import it.vincenzopicone.foodball.repository.EventoRepository;
import it.vincenzopicone.foodball.service.EventoService;
import it.vincenzopicone.foodball.service.PartitaService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/eventi")
public class EventoController {
	
	@Autowired EventoService eventoService;
	@Autowired EventoRepository repo;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<List<Evento>>(eventoService.getAllEvento(), HttpStatus.OK);
	}
	@GetMapping("/id/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(eventoService.getEvento(id), HttpStatus.OK);
	}
	
	@GetMapping("/data/{data}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getEventoPerData(@PathVariable LocalDate data){
		return new ResponseEntity<List<Evento>>(eventoService.getEventoPerData(data), HttpStatus.OK);
	}
	@GetMapping("/citta/{citta}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getEventoPerCitta(@PathVariable String citta){
		return new ResponseEntity<List<Evento>>(repo.findByCitta(citta), HttpStatus.OK);
	}
	@GetMapping("/citta/{citta}/squadra/{squadra}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getEventoPerCitta(@PathVariable String citta, String squadra){
		return new ResponseEntity<List<Evento>>(repo.findByCittaAndPartita(citta, squadra), HttpStatus.OK);
	}
	@GetMapping("/citta/{citta}/tipolocale/{locale}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getEventoPerCittaTipoLocale(@PathVariable String citta, @PathVariable String locale){
		TipoLocale tipolocale = null;
		if (locale.equals("PIZZERIA")) {
			tipolocale = TipoLocale.PIZZERIA;
		} else if (locale.equals("PUB")) {
			tipolocale = TipoLocale.PUB;
		} else if (locale.equals("BURGER")) {
			tipolocale = TipoLocale.BURGER;
		} else tipolocale = TipoLocale.RISTORANTE;

		return new ResponseEntity<List<Evento>>(repo.findByCittaAndTipolocale(citta, tipolocale), HttpStatus.OK);
	}
	@GetMapping("/nomelocale/{nome}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getEventoPerNomeLocale(@PathVariable String nome){
		return new ResponseEntity<List<Evento>>(repo.findByNomelocale(nome), HttpStatus.OK);
	}
	@GetMapping("/pageable")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Evento>> getAllPage(Pageable pag) {
		return new ResponseEntity<Page<Evento>>(eventoService.getAllEventoPageable(pag), HttpStatus.OK);
	}
	@GetMapping("/citta/{citta}/data/{data}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getEventoPerCittaData(@PathVariable String citta, @PathVariable LocalDate data){
		return new ResponseEntity<List<Evento>>(repo.findByCittaAndData(citta, data), HttpStatus.OK);
	}
	@GetMapping("/pageable/order/{citta}/data/{data}/page/{pageNumber}/size/{pageSize}")
	@PreAuthorize("isAuthenticated()")
	public Page<Evento> getEntitiesByCityAndDate(@PathVariable String citta, @PathVariable LocalDate data, @PathVariable int pageNumber, @PathVariable int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repo.findByCittaAndDataAfterOrderByCittaAscDataAsc(citta, data, pageable);
	}
	@PostMapping("/crea")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> newEvento (@RequestBody CreaNuovoEventoDto newEvento) {
		return new ResponseEntity<Evento>(eventoService.creaEvento(newEvento), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/rimuovi/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> rimuoviEvento(@PathVariable Long id) {
		return new ResponseEntity<>(eventoService.removeEvento(id), HttpStatus.OK);
	}
	

}
