package it.vincenzopicone.foodball.configuration;

import java.time.LocalDate;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;

import it.vincenzopicone.foodball.model.Partita;

@Configuration
public class PartitaConfiguration {
	
	@Bean("PartitaRandom")
	@Scope("prototype")
	public Partita partitaRandom(LocalDate data, Long giorno) {
		Faker fake = new Faker(new Locale("it-IT"));
		return Partita.builder()
//				.data(LocalDate.of(fake.number().numberBetween(2023, 2023),fake.number().numberBetween(5, 6), fake.number().numberBetween(1, 30)))
				.data(data.plusDays(giorno))
				.squadra1(fake.esports().team())
				.squadra2(fake.esports().team())
				.build();
	}

}
