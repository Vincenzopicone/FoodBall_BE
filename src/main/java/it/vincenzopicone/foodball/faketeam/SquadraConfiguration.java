package it.vincenzopicone.foodball.faketeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SquadraConfiguration {
	
	@Autowired SquadraRepository repo;
	
	
	
	public void addSquadra () {
		Squadra S1 = new Squadra("Juventus", "SerieA");
		repo.save(S1);
		Squadra S2 = new Squadra("Inter", "SerieA");
		repo.save(S2);
		Squadra S3 = new Squadra("Milan", "SerieA");
		repo.save(S3);
		Squadra S4 = new Squadra("Roma", "SerieA");
		repo.save(S4);
		Squadra S5 = new Squadra("Lazio", "SerieA");
		repo.save(S5);
		Squadra S6 = new Squadra("Napoli", "SerieA");
		repo.save(S6);
		Squadra S7 = new Squadra("Fiorentina", "SerieA");
		repo.save(S7);
		Squadra S8 = new Squadra("Atalanta", "SerieA");
		repo.save(S8);
		/////
		Squadra S9 = new Squadra("Manchester City", "Premier League");
		repo.save(S9);
		Squadra S10 = new Squadra("Manchester UTD", "Premier League");
		repo.save(S10);
		Squadra S11 = new Squadra("Liverpool", "Premier League");
		repo.save(S11);
		Squadra S12 = new Squadra("Chelsea", "Premier League");
		repo.save(S12);
		Squadra S13 = new Squadra("Tottenham", "Premier League");
		repo.save(S13);
		Squadra S14 = new Squadra("Arsenal", "Premier League");
		repo.save(S14);
		Squadra S15 = new Squadra("Brighton", "Premier League");
		repo.save(S15);
		Squadra S16 = new Squadra("Everton", "Premier League");
		repo.save(S16);
		//////
		Squadra S17 = new Squadra("Barcelona", "Liga");
		repo.save(S17);
		Squadra S18 = new Squadra("Real Madrid", "Liga");
		repo.save(S18);
		Squadra S19 = new Squadra("Atl. Madrid", "Liga");
		repo.save(S19);
		Squadra S20 = new Squadra("Valencia", "Liga");
		repo.save(S20);
		Squadra S21 = new Squadra("Siviglia", "Liga");
		repo.save(S21);
		Squadra S22 = new Squadra("Betis", "Liga");
		repo.save(S22);
		Squadra S23 = new Squadra("Villareal", "Liga");
		repo.save(S23);
		Squadra S24 = new Squadra("Espanyol", "Liga");
		repo.save(S24);
		//////
		Squadra S25 = new Squadra("PSG", "Ligue1");
		repo.save(S25);
		Squadra S26 = new Squadra("Lione", "Ligue1");
		repo.save(S26);
		Squadra S27 = new Squadra("Marsiglia", "Ligue1");
		repo.save(S27);
		Squadra S28 = new Squadra("Rennes", "Ligue1");
		repo.save(S28);
		Squadra S29 = new Squadra("Monaco", "Ligue1");
		repo.save(S29);
		Squadra S30 = new Squadra("Lille", "Ligue1");
		repo.save(S30);
		////
		Squadra S31 = new Squadra("Bayern Monaco", "Bundesliga");
		repo.save(S31);
		Squadra S32 = new Squadra("Dortmund", "Bundesliga");
		repo.save(S32);
		Squadra S33 = new Squadra("Lipsia", "Bundesliga");
		repo.save(S33);
		Squadra S34 = new Squadra("Leverkusen", "Bundesliga");
		repo.save(S34);
		Squadra S35 = new Squadra("Wolfsburg", "Bundesliga");
		repo.save(S35);
		Squadra S36 = new Squadra("Friburgo", "Bundesliga");
		repo.save(S36);
	}

}
