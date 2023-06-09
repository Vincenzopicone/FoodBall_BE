package it.vincenzopicone.foodball.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.vincenzopicone.foodball.model.Locale;
import it.vincenzopicone.foodball.model.Partita;
import it.vincenzopicone.foodball.repository.LocaleRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LocaleService {
	
	@Autowired @Qualifier("PizzeriaRandom") private ObjectProvider<Locale> pizzeriaProvider;
	@Autowired @Qualifier("PubRandom") private ObjectProvider<Locale> pubProvider;
	@Autowired @Qualifier("BurgerRandom") private ObjectProvider<Locale> burgerProvider;
	@Autowired @Qualifier("RistoranteRandom") private ObjectProvider<Locale> ristoranteProvider;
    @Autowired LocaleRepository repo;

	public Locale creaLocale(Locale L) {
		return repo.save(L);
	}
	
	public Locale creaPizzeriaRandom (Integer i, String citta) {
		Locale L = pizzeriaProvider.getObject(i, citta);
		return repo.save(L);
	}
	public Locale creaPubRandom (Integer i, String citta) {
		Locale L = pubProvider.getObject(i, citta);
		return repo.save(L);
	}
	public Locale creaRistoranteRandom (Integer i, String citta) {
		Locale L = ristoranteProvider.getObject(i, citta);
		return repo.save(L);
	}
	public Locale creaBurgerRandom (Integer i, String citta) {
		Locale L = burgerProvider.getObject(i, citta);
		return repo.save(L);
	}

	public List<Locale> getAllLocale() {
		return (List<Locale>) repo.findAll();
	}
	public Locale getLocale(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityNotFoundException("Il locale non esiste!");
		}
		return repo.findById(id).get();
	}
	public Page<Locale> getAllLocalePageable(Pageable pageable) {
		return (Page<Locale>) repo.findAll(pageable);
	}
	
	public String removeLocale(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Il locale non esiste!");
		}
		repo.deleteById(id);
		return "Locale cancellato!";
	}
	public Locale updateLocale(Locale locale) {
		if(!repo.existsById(locale.getId())) {
			throw new EntityExistsException("Il locale non esiste!");
		}
		repo.save(locale);
		return locale;
	}
	public List<Locale> getAllLocaleByName(String name){
		return (List<Locale>) repo.searchByPartName(name);
	}

}
