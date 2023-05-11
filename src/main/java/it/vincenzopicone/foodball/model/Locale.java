package it.vincenzopicone.foodball.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.vincenzopicone.foodball.auth.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "locali")
public class Locale {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable = false)
	private String nomelocale;
	@Column(nullable= false)
	private String indirizzo;
	@Column(nullable= false)
	private String citta;
	@Enumerated(EnumType.STRING)
	@Column(nullable= false)
	private TipoLocale tipolocale;
//	@OneToMany(mappedBy = "locale", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JsonIgnoreProperties({"evento"})
//	private List<Prenotazione> prenotazioni;
	@OneToMany(mappedBy = "locale", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"locale"})
	private List<Evento> evento;;
	
	

}
