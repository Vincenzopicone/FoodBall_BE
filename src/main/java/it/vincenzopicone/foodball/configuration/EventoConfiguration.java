package it.vincenzopicone.foodball.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.vincenzopicone.foodball.model.Evento;
import it.vincenzopicone.foodball.model.Locale;
import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.repository.LocaleRepository;
import it.vincenzopicone.foodball.repository.PartitaRepository;

@Configuration
public class EventoConfiguration {
	
	@Autowired LocaleRepository localeRepo;
	@Autowired PartitaRepository partitaRepo;

	@Bean("EventoRandom")
	@Scope("prototype")
	public Evento eventoRandom () {
		Partita P = partitaRepo.findByPartitaRandom();
		Locale L = localeRepo.findByLocaleRandom();
		return Evento.builder()
				.locale(L)
				.partita(P)
				.data(P.getData())
				.citta(L.getCitta())
				.postidisponibili(30)
				.build();
	}
}
