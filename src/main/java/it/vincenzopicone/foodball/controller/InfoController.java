package it.vincenzopicone.foodball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vincenzopicone.foodball.auth.entity.Info;
import it.vincenzopicone.foodball.service.InfoService;

@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/auth")
public class InfoController {
	
	@Autowired InfoService infoService;
	
//	@PostMapping(value = {"/register/info", "/signup/info"})
//    public ResponseEntity<Info> register(@RequestBody Info info){
//        return new ResponseEntity<Info>(infoService.creaInfo(info), HttpStatus.CREATED);
//    }

}
