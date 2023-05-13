package it.vincenzopicone.foodball.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "eventi")
public class Evento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable=false)
	private String citta;
	@Column(nullable= false)
	private LocalDate data;
	@Column(nullable= false)
	private Integer postidisponibili;
	@ManyToOne
	@JsonIgnoreProperties({"evento"})
	private Locale locale;
	@ManyToOne
	@JsonIgnoreProperties({"evento"})
	private Partita partita;
	@OneToMany(mappedBy ="evento", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "evento" , "utente"})
	private List<Prenotazione> prenotazione;

}
