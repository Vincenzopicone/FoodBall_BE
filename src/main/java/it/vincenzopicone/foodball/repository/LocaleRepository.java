package it.vincenzopicone.foodball.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.vincenzopicone.foodball.model.Locale;
import it.vincenzopicone.foodball.model.Partita;

public interface LocaleRepository extends CrudRepository<Locale, Long>, PagingAndSortingRepository<Locale, Long> {
	
	public boolean existsByNomelocale(String nomelocale);
	public boolean existsByCitta(String citta);

	public List<Locale> findByNomelocale(String nomelocale);
	public List<Locale> findByCitta(String citta);
	@Query(value="SELECT l FROM Locale l ORDER BY RANDOM() LIMIT 1")
	Locale findByLocaleRandom();
	
	@Query("SELECT l FROM Locale l WHERE LOWER(l.nomelocale) LIKE LOWER('%' || :name || '%')")
	public List<Locale> searchByPartName(String name);
	
	@Query("SELECT l FROM Locale l WHERE LOWER(l.citta) LIKE LOWER('%' || :name || '%')")
	public List<Locale> searchByPartNameCitta(String name);

}
