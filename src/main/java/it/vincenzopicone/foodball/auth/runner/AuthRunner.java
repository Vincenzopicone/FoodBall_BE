package it.vincenzopicone.foodball.auth.runner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.vincenzopicone.foodball.auth.entity.ERole;
import it.vincenzopicone.foodball.auth.entity.Role;
import it.vincenzopicone.foodball.auth.payload.RegisterDto;
import it.vincenzopicone.foodball.auth.repository.RoleRepository;
import it.vincenzopicone.foodball.auth.repository.UserRepository;
import it.vincenzopicone.foodball.auth.service.AuthService;
import it.vincenzopicone.foodball.auth.service.AuthServiceImpl;
import it.vincenzopicone.foodball.configuration.EventoConfiguration;
import it.vincenzopicone.foodball.configuration.PrenotazioneConfiguration;
import it.vincenzopicone.foodball.model.Evento;
import it.vincenzopicone.foodball.model.Prenotazione;
import it.vincenzopicone.foodball.repository.EventoRepository;
import it.vincenzopicone.foodball.service.EventoService;
import it.vincenzopicone.foodball.service.LocaleService;
import it.vincenzopicone.foodball.service.PartitaService;
import it.vincenzopicone.foodball.service.PrenotazioneService;


@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired RoleRepository roleRepository;
	@Autowired UserRepository userRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired AuthService authService;
	@Autowired PartitaService partitaService;
	@Autowired LocaleService localeService;
	@Autowired EventoService eventoService;
	@Autowired EventoConfiguration eventoConfiguration;
	@Autowired PrenotazioneConfiguration prenotazioneConfiguration;
	@Autowired PrenotazioneService prenotazioneService;
	@Autowired EventoRepository eventoRepository;
	
	private Set<Role> userRole;
	private Set<Role> adminRole;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		//setRoleDefault();
		//setPartitaRandom();
		//setLocaleRandom();
		//setEventiRandom();
		//setPrenotazioniRandom();
		
		
	}
	
	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);
		
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);
		
		
		adminRole = new HashSet<Role>();
		adminRole.add(admin);
		
		
		userRole = new HashSet<Role>();
		userRole.add(user);
	}
	
	public void setPartitaRandom() {
		for(Long i = 0l; i < 30l; i++) {
		partitaService.creaPartitaRandom(LocalDate.of(2023, 6, 1), i);
		}
	}
	public void setLocaleRandom() {
		for(Integer i = 0; i < 30l; i++) {
		localeService.creaLocaleRandom(i);
		}
	}
	
	public void setEventiRandom() {
		for(Integer i = 0; i < 30l; i++) {
			Evento E = eventoConfiguration.eventoRandom();
			eventoService.creaEventoRandom(E);
			}
	}
	
	public void setPrenotazioniRandom() {
		for(Integer i = 0; i < 30l; i++) {
			Prenotazione P = prenotazioneConfiguration.prenotazioneRandom();
			prenotazioneService.creaPrenotazione(P);
			}
	}
	
}
