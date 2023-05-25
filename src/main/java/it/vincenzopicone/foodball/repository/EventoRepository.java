package it.vincenzopicone.foodball.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.vincenzopicone.foodball.model.Evento;
import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.model.TipoLocale;


public interface EventoRepository extends CrudRepository<Evento, Long>, PagingAndSortingRepository<Evento, Long> {

	public boolean existsByData(LocalDate data);
	Optional <Evento> findById(Long id);
	public List <Evento> findByData(LocalDate data);
//	List<Evento> findByCitta(String citta);
	List<Evento> findByCittaAndData(String citta, LocalDate data);
	
	@Query(value="SELECT e FROM Evento e ORDER BY RANDOM() LIMIT 1")
	Evento findByEventoRandom();
	
	Page<Evento> findByCittaAndDataAfterOrderByCittaAscDataAsc(String city, LocalDate date, Pageable pageable);
	
//	@Query("SELECT e FROM Evento e WHERE e.locale.nomelocale = :nome")
//	List<Evento> findByNomelocale(String nome);
	
	@Query("SELECT e FROM Evento e WHERE LOWER (e.citta) LIKE LOWER('%' || :citta || '%') ORDER BY e.data ASC")
	List<Evento> findByCitta(String citta);
	
	@Query("SELECT e FROM Evento e WHERE LOWER (e.locale.nomelocale) LIKE LOWER('%' || :nome || '%') ORDER BY e.data ASC")
	List<Evento> findByNomelocale(String nome);
	
	@Query("SELECT e FROM Evento e WHERE LOWER (e.locale.tipolocale) LIKE LOWER('%' || :tipolocale || '%') ORDER BY e.data ASC")
	List<Evento> findByTipolocale(String tipolocale);
	
	@Query("SELECT e FROM Evento e WHERE LOWER (e.citta) LIKE LOWER('%' || :citta || '%') AND e.locale.tipolocale = :locale ORDER BY e.data ASC" )
	List<Evento> findByCittaAndTipolocale (String citta, TipoLocale locale);
	
	@Query("SELECT e FROM Evento e WHERE LOWER (e.citta) LIKE LOWER('%' || :citta || '%') AND e.partita.squadra1 = :squadra ORDER BY e.data ASC" )
	List<Evento> findByCittaAndPartita (String citta, String squadra);
	
}
