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
public class CreaNuovoEventoDto {
	
	private Long idLocale;
	private Long idPartita;
	private Integer postiDisponibili;

}
