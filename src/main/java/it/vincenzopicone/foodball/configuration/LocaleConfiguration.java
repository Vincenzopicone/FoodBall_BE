package it.vincenzopicone.foodball.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;

import it.vincenzopicone.foodball.model.Locale;
import it.vincenzopicone.foodball.model.TipoLocale;



@Configuration
public class LocaleConfiguration {
	
	@Bean("PizzeriaRandom")
	@Scope("prototype")
	public Locale pizzeriaRandom(Integer i, String città) {
		Faker fake = new Faker(new java.util.Locale("it-IT"));
		return Locale.builder()
				.nomelocale("FakePizzeria " + i)
				.citta(città)
				.indirizzo("Via " + fake.name().firstName() + "," + fake.number().numberBetween(1, 99))
				.tipolocale(TipoLocale.PIZZERIA)
				.build();
	}
	@Bean("PubRandom")
	@Scope("prototype")
	public Locale pubRandom(Integer i, String città) {
		Faker fake = new Faker(new java.util.Locale("it-IT"));
		return Locale.builder()
				.nomelocale("FakePub " + i)
				.citta(città)
				.indirizzo("Via " + fake.name().firstName() + "," + fake.number().numberBetween(1, 99))
				.tipolocale(TipoLocale.PUB)
				.build();
	}
	
	@Bean("RistoranteRandom")
	@Scope("prototype")
	public Locale ristoranteRandom(Integer i, String città) {
		Faker fake = new Faker(new java.util.Locale("it-IT"));
		return Locale.builder()
				.nomelocale("FakeRistorante " + i)
				.citta(città)
				.indirizzo("Via " + fake.name().firstName() + "," + fake.number().numberBetween(1, 99))
				.tipolocale(TipoLocale.RISTORANTE)
				.build();
	}
	@Bean("BurgerRandom")
	@Scope("prototype")
	public Locale burgerRandom(Integer i, String città) {
		Faker fake = new Faker(new java.util.Locale("it-IT"));
		return Locale.builder()
				.nomelocale("FakeBurger " + i)
				.citta(città)
				.indirizzo("Via " + fake.name().firstName() + "," + fake.number().numberBetween(1, 99))
				.tipolocale(TipoLocale.BURGER)
				.build();
	}

}
