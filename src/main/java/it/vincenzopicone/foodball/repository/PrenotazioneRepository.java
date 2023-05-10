package it.vincenzopicone.foodball.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.model.Prenotazione;

public interface PrenotazioneRepository
		extends CrudRepository<Prenotazione, Long>, PagingAndSortingRepository<Prenotazione, Long> {
	public boolean existsByData(LocalDate data);
	
	public List <Prenotazione> findByData(LocalDate data);
	
//	@Query(value="SELECT p FROM Prenotazione p ORDER BY RANDOM() LIMIT 1")
//	Prenotazione findByPrenotazioneRandom();
//	@Query(value="SELECT p FROM Prenotazione p WHERE p.utente = :id")
//	Prenotazione findByUtente(Long id);
//	@Query(value="SELECT p FROM Prenotazione p WHERE p.partita = :id")
//	Prenotazione findByPartita(Long id);
//	@Query(value="SELECT p FROM Prenotazione p WHERE p.locale = :id")
//	Prenotazione findByLocale(Long id);
//
//	@Query(value="SELECT p FROM Prenotazione p WHERE p.utente = :id AND p.data = :data")
//	Prenotazione findByUtenteData(Long id, LocalDate data);
//	@Query(value="SELECT p FROM Prenotazione p WHERE p.locale = :id AND p.data = :data")
//	Prenotazione findByLocaleData(Long id, LocalDate data);
//	@Query(value="SELECT p FROM Prenotazione p WHERE p.partita = :id AND p.data = :data")
//	Prenotazione findByPartitaData(Long id, LocalDate data);
//	@Query(value="SELECT p FROM Prenotazione p WHERE p.partita = :idPartita AND p.locale = :idlocale")
//	Prenotazione findByPartitaLocale(Long idPartita, Long idLocale);
}
