package it.vincenzopicone.foodball.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.model.Prenotazione;

public interface PrenotazioneRepository
		extends JpaRepository<Prenotazione, Long>, PagingAndSortingRepository<Prenotazione, Long> {
	public boolean existsByDataevento(LocalDate data);	
	public List <Prenotazione> findByDataevento(LocalDate data);
	public boolean existsByDataprenotazione(LocalDate data);	
	public List <Prenotazione> findByDataprenotazione(LocalDate data);
	

	
//	 @Query("SELECT COUNT(p) > 0 FROM Prenotazione p WHERE p.dataevento = :data AND p.evento = :evento AND p.utente = :utente")
//	 boolean existsByDataeventoAndEventoAndUtente(@Param("data") LocalDate data, @Param("evento") Long evento, @Param("utente") Long utente);
	
//	@Modifying
//	@Query(value="DELETE FROM Prenotazione p WHERE p.id = :id")
//	void rimuoviPrenotazione(Long id);
	
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
