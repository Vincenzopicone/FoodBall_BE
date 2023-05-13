package it.vincenzopicone.foodball.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.vincenzopicone.foodball.model.Evento;


public interface EventoRepository extends CrudRepository<Evento, Long>, PagingAndSortingRepository<Evento, Long> {

	public boolean existsByData(LocalDate data);
	public List <Evento> findByData(LocalDate data);
	List<Evento> findByCitta(String citta);
	List<Evento> findByCittaAndData(String citta, LocalDate data);
	@Query(value="SELECT e FROM Evento e ORDER BY RANDOM() LIMIT 1")
	Evento findByEventoRandom();
}
