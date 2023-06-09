package it.vincenzopicone.foodball.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.vincenzopicone.foodball.faketeam.Squadra;
import it.vincenzopicone.foodball.faketeam.SquadraRepository;
import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.repository.PartitaRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PartitaService {
	
	@Autowired PartitaRepository repo;
	@Autowired SquadraRepository squadraRepository;
	@Autowired @Qualifier("PartitaRandom") private ObjectProvider<Partita> randomPartitaProvider;
	@Autowired @Qualifier("PartitaDefault") private ObjectProvider<Partita> defaultPartitaProvider;
	
	
	public Partita creaPartitaRandom(LocalDate date, Long giorno) {
		Partita P = randomPartitaProvider.getObject(date, giorno);
		return repo.save(P);
	}
	
	public void creaPartitaDefault(LocalDate date, Long giorno) {
		Squadra S1 = squadraRepository.findBySquadraRandom();
		Squadra S2 = squadraRepository.findBySquadraRandom();
		Partita P = defaultPartitaProvider.getObject(date, giorno, S1.getNome(), S2.getNome());
		repo.save(P);
	}
	
	
	public List<Partita> getAllPartite() {
		return (List<Partita>) repo.findAll();
	}
	public Partita getPartita(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityNotFoundException("La partita non esiste!");
		}
		return repo.findById(id).get();
	}
	
	public Page<Partita> getAllPartitaPageable(Pageable pageable) {
		return (Page<Partita>) repo.findAll(pageable);
	}
	
	public List <Partita> getPartitaPerData(LocalDate dataInserimento) {
		if(!repo.existsByData(dataInserimento)) {
			throw new EntityNotFoundException("Non esistono partite con la data indicata!");
		}
		return repo.findByData(dataInserimento);
	}
	public Partita getPartitaRandom() {
		return repo.findByPartitaRandom();
	}
	public String removePartita(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("La partita non esiste!");
		}
		repo.deleteById(id);
		return "Partita cancellata!";
	}
	public Partita updatePartita(Partita partita) {
		if(!repo.existsById(partita.getId())) {
			throw new EntityExistsException("La partita non esiste!");
		}
		repo.save(partita);
		return partita;
	}

}
