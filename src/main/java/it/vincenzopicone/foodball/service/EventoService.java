package it.vincenzopicone.foodball.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.vincenzopicone.foodball.model.Evento;
import it.vincenzopicone.foodball.repository.EventoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EventoService {
	
	@Autowired EventoRepository repo;

	
	public Evento creaEventoRandom(Evento evento) {
		return repo.save(evento);
	}
	
	public List<Evento> getAllEvento() {
		return (List<Evento>) repo.findAll();
	}
	public Evento getEvento(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityNotFoundException("L'evento non esiste!");
		}
		return repo.findById(id).get();
	}
	
	public Page<Evento> getAllEventoPageable(Pageable pageable) {
		return (Page<Evento>) repo.findAll(pageable);
	}
	
	public List <Evento> getEventoPerData(LocalDate dataInserimento) {
		if(!repo.existsByData(dataInserimento)) {
			throw new EntityNotFoundException("Non esistono eventi con la data indicata!");
		}
		return repo.findByData(dataInserimento);
	}
	public Evento getEventoRandom() {
		return repo.findByEventoRandom();
	}
	public String removeEvento(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("L'evento non esiste!");
		}
		repo.deleteById(id);
		return "Evento cancellato!";
	}
	public Evento updateEvento(Evento evento) {
		if(!repo.existsById(evento.getId())) {
			throw new EntityExistsException("La partita non esiste!");
		}
		repo.save(evento);
		return evento;
	}

}
