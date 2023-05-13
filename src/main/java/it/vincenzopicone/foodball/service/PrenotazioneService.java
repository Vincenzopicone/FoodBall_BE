package it.vincenzopicone.foodball.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import it.vincenzopicone.foodball.auth.entity.User;
import it.vincenzopicone.foodball.auth.repository.UserRepository;
import it.vincenzopicone.foodball.model.Evento;
import it.vincenzopicone.foodball.model.Locale;
import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.model.Prenotazione;
import it.vincenzopicone.foodball.repository.EventoRepository;
import it.vincenzopicone.foodball.repository.PrenotazioneRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrenotazioneService {
	
	@Autowired PrenotazioneRepository repo;
	@Autowired EventoRepository eventoRepo;
	@Autowired EventoService eventoService;
	@Autowired UserRepository utenteRepo;
	
	
	public Prenotazione creaPrenotazione(Prenotazione prenotazione) {
		Evento E = eventoRepo.findById(prenotazione.getEvento().getId()).get();
		User U = utenteRepo.findByUsername(prenotazione.getUtente().getUsername()).get();
		Prenotazione P = new Prenotazione();
		if(E.getPostidisponibili() > prenotazione.getNumeropersone()) {
		E.setPostidisponibili(E.getPostidisponibili() - prenotazione.getNumeropersone());
		eventoService.updateEvento(E);
		P.setDataprenotazione(LocalDate.now());
		P.setDataevento(E.getData());
		P.setUtente(U);
		P.setNumeropersone(prenotazione.getNumeropersone());
		P.setEvento(E);
		repo.save(prenotazione);
		} 
	    return P;
	}

	
	public List<Prenotazione> getAll() {
		return (List<Prenotazione>) repo.findAll();
	}
	public Prenotazione getPrenotazione(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityNotFoundException("La Prenotazione non esiste!");
		}
		return repo.findById(id).get();
	}
	
	public Page<Prenotazione> getAllPrenotazionePageable(Pageable pageable) {
		return (Page<Prenotazione>) repo.findAll(pageable);
	}

	
	public List <Prenotazione> getPrenotazionePerDataprenotazione(LocalDate dataInserimento) {
		if(!repo.existsByDataprenotazione(dataInserimento)) {
			throw new EntityNotFoundException("Non esistono prenotazioni con la data indicata!");
		}
		return repo.findByDataprenotazione(dataInserimento);
	}
	public List <Prenotazione> getPrenotazionePerDataevento(LocalDate dataInserimento) {
		if(!repo.existsByDataevento(dataInserimento)) {
			throw new EntityNotFoundException("Non esistono prenotazioni con la data indicata!");
		}
		return repo.findByDataevento(dataInserimento);
	}

	public String removePrenotazione(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("La prenotazione non esiste!");
		}
		repo.deleteById(id);
		return "Prenotazione cancellata!";
	}
	public Prenotazione updatePrenotazione(Prenotazione prenotazione) {
		if(!repo.existsById(prenotazione.getId())) {
			throw new EntityExistsException("La prenotazione non esiste!");
		}
		repo.save(prenotazione);
		return prenotazione;
	}

}
