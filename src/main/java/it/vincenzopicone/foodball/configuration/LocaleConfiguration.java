package it.vincenzopicone.foodball.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;

import it.vincenzopicone.foodball.model.Locale;
import it.vincenzopicone.foodball.model.TipoLocale;



@Configuration
public class LocaleConfiguration {
	
	@Bean("LocaleRandom")
	@Scope("prototype")
	public Locale localeRandom(Integer i) {
		Faker fake = new Faker();
		return Locale.builder()
				.nomelocale("Locale " + i)
				.citta("Milano")
				.indirizzo(fake.address().streetAddress()+ " , " +fake.address().streetAddressNumber())
				.tipolocale(TipoLocale.RISTORANTE)
				.build();
	}

}
