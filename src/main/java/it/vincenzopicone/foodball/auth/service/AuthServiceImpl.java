package it.vincenzopicone.foodball.auth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.vincenzopicone.foodball.auth.entity.ERole;
import it.vincenzopicone.foodball.auth.entity.Info;
import it.vincenzopicone.foodball.auth.entity.Role;
import it.vincenzopicone.foodball.auth.entity.User;
import it.vincenzopicone.foodball.auth.exception.MyAPIException;
import it.vincenzopicone.foodball.auth.payload.LoginDto;
import it.vincenzopicone.foodball.auth.payload.RegisterDto;
import it.vincenzopicone.foodball.auth.repository.InfoRepository;
import it.vincenzopicone.foodball.auth.repository.RoleRepository;
import it.vincenzopicone.foodball.auth.repository.UserRepository;
import it.vincenzopicone.foodball.auth.security.JwtTokenProvider;
import it.vincenzopicone.foodball.model.TipoLocale;
import it.vincenzopicone.foodball.service.InfoService;


@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    @Autowired InfoRepository infoRepo;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        
    	Authentication authentication = authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(
        				loginDto.getUsername(), loginDto.getPassword()
        		)
        ); 
    	
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Username già utilizzato!");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email già registrata!");
        }
        if(registerDto.getEmail() == null){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Inserire l'email per la registrazione!");
        }
        if(registerDto.getUsername() == null){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Inserire l'username per la registrazione!");
        }
        if(registerDto.getPassword() == null){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Inserire la password per la registrazione!");
        }
     

        User user = new User();
        Info info = new Info();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        
//        if(registerDto.getInfo() != null) {
//        	infoRepo.save(info);
//        
//        if(registerDto.getInfo().getCitta() != null) {
//        info.setCitta(registerDto.getInfo().getCitta());
//        } else {
//        	info.setCitta(null);
//        }
//        if(registerDto.getInfo().getIndirizzo() != null) {
//        info.setIndirizzo(registerDto.getInfo().getIndirizzo());
//        } else {
//        	info.setIndirizzo(null);
//        }
//        if(registerDto.getInfo().getTelefono() != null) {
//            info.setTelefono(null);
//            } else {
//            	info.setTelefono(null);
//            }
//        if(registerDto.getInfo().getPartitaiva() != null) {
//            info.setPartitaiva(registerDto.getInfo().getPartitaiva());
//            } else {
//            	info.setPartitaiva(null);
//            }
//        if(registerDto.getInfo().getTipolocale() != null) {
//        	info.setTipolocale(registerDto.getInfo().getTipolocale());
//        } else { info.setTipolocale(null);
//        
//        }
//        }
        
//        if(registerDto.getInfo().getTipolocale().equals("RISTORANTE")) {
//        	info.setTipolocale(TipoLocale.RISTORANTE);
//        } else if (registerDto.getInfo().getTipolocale().equals("BURGER")) {
//        	info.setTipolocale(TipoLocale.BURGER);
//        } else if (registerDto.getInfo().getTipolocale().equals("PIZZERIA")) {
//        	info.setTipolocale(TipoLocale.PIZZERIA);
//        } else info.setTipolocale(TipoLocale.PUB);
//        user.setInfo(info);

        
        
        // Per registrare tutti come USER di Default commentare IF
        if(registerDto.getRoles() != null) {
	        registerDto.getRoles().forEach(role -> {
	        	Role userRole = roleRepository.findByRoleName(getRole(role)).get();
	        	roles.add(userRole);
	        });
        } else {
        	Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER).get();
        	roles.add(userRole);
        }
        
        
        user.setRoles(roles);
        System.out.println(user);
        
        userRepository.save(user);

        return "User registered successfully!.";
    }
    
    public ERole getRole(String role) {
    	if(role.equals("ROLE_ADMIN")) return ERole.ROLE_ADMIN;
    	else return ERole.ROLE_USER;
    }
    
}
