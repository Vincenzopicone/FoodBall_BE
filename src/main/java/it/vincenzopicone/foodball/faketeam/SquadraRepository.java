package it.vincenzopicone.foodball.faketeam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.vincenzopicone.foodball.model.Evento;

public interface SquadraRepository extends CrudRepository<Squadra, Long> {

	@Query(value="SELECT s FROM Squadra s ORDER BY RANDOM() LIMIT 1")
	Squadra findBySquadraRandom();
}
