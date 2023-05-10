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
import it.vincenzopicone.foodball.auth.repository.RoleRepository;
import it.vincenzopicone.foodball.auth.repository.UserRepository;
import it.vincenzopicone.foodball.auth.service.AuthService;
import it.vincenzopicone.foodball.service.LocaleService;
import it.vincenzopicone.foodball.service.PartitaService;


@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired RoleRepository roleRepository;
	@Autowired UserRepository userRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired AuthService authService;
	@Autowired PartitaService partitaService;
	@Autowired LocaleService localeService;
	
	private Set<Role> userRole;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		//setRoleDefault();
		setPartitaRandom();
		//setLocaleRandom();
		
		
	}
	
	private void setRoleDefault() {
//		Role restaurant = new Role();
//		restaurant.setRoleName(ERole.ROLE_RESTAURANT);
//		roleRepository.save(restaurant);
		
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);
		
		
//		restaurantRole = new HashSet<Role>();
//		restaurantRole.add(restaurant);
		
		
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

}
