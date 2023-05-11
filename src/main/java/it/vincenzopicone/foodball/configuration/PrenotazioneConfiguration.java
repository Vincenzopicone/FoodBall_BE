package it.vincenzopicone.foodball.configuration;

import java.time.LocalDate;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.github.javafaker.Faker;

import it.vincenzopicone.foodball.auth.entity.User;
import it.vincenzopicone.foodball.auth.repository.UserRepository;
import it.vincenzopicone.foodball.model.Evento;
import it.vincenzopicone.foodball.model.Prenotazione;
import it.vincenzopicone.foodball.repository.EventoRepository;

@Configuration
public class PrenotazioneConfiguration {
	
	@Autowired EventoRepository eventoRepo;
	@Autowired UserRepository userRepo;
	
	@Bean("PrenotazioneRandom")
	@Scope("prototype")
	public Prenotazione prenotazioneRandom() {
		Faker fake = new Faker(new Locale("it-IT"));
		Evento E = eventoRepo.findByEventoRandom();
		User U = userRepo.findByUserRandom();
		return Prenotazione.builder()
				.utente(U)
				.evento(E)
				.dataevento(E.getData())
				.dataprenotazione(LocalDate.of(2023, 5, 1))
				.numeropersone(20)
				.build();
	}

}
