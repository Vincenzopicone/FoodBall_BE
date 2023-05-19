package it.vincenzopicone.foodball.payload;

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
public class CreaNuovoLocaleDto {
	
	private String username;
	private String nomelocale;
	private String indirizzolocale;
	private String citta;
	private String tipolocale;

}
