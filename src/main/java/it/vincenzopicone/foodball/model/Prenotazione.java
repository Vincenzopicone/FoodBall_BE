package it.vincenzopicone.foodball.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.vincenzopicone.foodball.auth.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"prenotazioni", "roles", "username", "password"})
	private User utente;

	private LocalTime orario;
	private String note;
	@Column(nullable = false)
	private LocalDate dataevento;
	@Column(nullable = false)
	private LocalDate dataprenotazione;
	@Column(nullable = false)
	private int numeropersone;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"prenotazione"})
	private Evento evento;
}
