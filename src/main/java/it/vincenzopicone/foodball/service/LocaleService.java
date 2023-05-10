package it.vincenzopicone.foodball.service;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.vincenzopicone.foodball.model.Locale;
import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.repository.LocaleRepository;

@Service
public class LocaleService {
	
	@Autowired @Qualifier("LocaleRandom") private ObjectProvider<Locale> randomLocaleProvider;
@Autowired LocaleRepository repo;

	public Locale creaLocale(Locale L) {
		return repo.save(L);
	}
	
	public Locale creaLocaleRandom (Integer i) {
		Locale L = randomLocaleProvider.getObject(i);
		return repo.save(L);
	}

}
