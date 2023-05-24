package it.vincenzopicone.foodball.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;

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
		Faker fake = new Faker(new java.util.Locale("it-IT"));
		Partita P = partitaRepo.findByPartitaRandom();
		Locale L = localeRepo.findByLocaleRandom();
		return Evento.builder()
				.locale(L)
				.partita(P)
				.data(P.getData())
				.citta(L.getCitta())
				.postidisponibili(fake.number().numberBetween(25, 50))
				.build();
	}
}
