package it.vincenzopicone.foodball.auth.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.vincenzopicone.foodball.model.Prenotazione;
import it.vincenzopicone.foodball.model.TipoLocale;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "info")
public class Info {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String indirizzo;
	private String citta;
	private Long telefono;
	private String partitaiva;
	private String tipolocale;
//	@OneToOne
//	private User utente;

}
