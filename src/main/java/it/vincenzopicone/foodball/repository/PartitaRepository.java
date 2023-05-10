package it.vincenzopicone.foodball.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import it.vincenzopicone.foodball.model.Partita;

public interface PartitaRepository extends CrudRepository<Partita, Long>, PagingAndSortingRepository<Partita, Long> {

	public boolean existsBySquadra1(String squadra1);
	public boolean existsBySquadra2(String squadra2);
	public boolean existsByData(LocalDate data);
	public List<Partita> findBySquadra1(String squadra1);
	public List<Partita> findBySquadra2(String squadra2);	
	public List <Partita> findByData(LocalDate data);
	
	@Query(value="SELECT p FROM Partita p ORDER BY RANDOM() LIMIT 1")
	Partita findByPartitaRandom();
}
