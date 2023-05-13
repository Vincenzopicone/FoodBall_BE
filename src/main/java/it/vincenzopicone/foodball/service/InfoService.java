package it.vincenzopicone.foodball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vincenzopicone.foodball.auth.entity.Info;
import it.vincenzopicone.foodball.auth.entity.User;
import it.vincenzopicone.foodball.auth.repository.InfoRepository;
import it.vincenzopicone.foodball.auth.repository.UserRepository;
import jakarta.persistence.EntityExistsException;

@Service
public class InfoService {
	
	@Autowired InfoRepository repo;
	@Autowired UserRepository userRepo;
	
//	public Info creaInfo (Info info) {
//		Info I = new Info();
//		User U = userRepo.findByUsername(info.getUtente().getUsername()).get();
//		I.setCitta(info.getCitta());
//		I.setIndirizzo(info.getIndirizzo());
//		I.setPartitaiva(info.getPartitaiva());
//		I.setTelefono(info.getTelefono());
//		I.setUtente(info.getUtente());
//		return repo.save(info);
//	}
	public Info modificaInfo (Info info) {
		if(!repo.existsById(info.getId())) {
			throw new EntityExistsException("Non sono state inserite informazioni!");
		}
		return repo.save(info);
	}
	public String removeInfo(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Non sono state inserite informazioni");
		}
		repo.deleteById(id);
		return "Informazioni cancellate!";
	}

}
