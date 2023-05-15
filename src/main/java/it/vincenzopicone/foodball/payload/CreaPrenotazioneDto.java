package it.vincenzopicone.foodball.payload;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreaPrenotazioneDto {
	private Long idevento;
	private String usernameutente;
	private int numeropersone;
	

}
