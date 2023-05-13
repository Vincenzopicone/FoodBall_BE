package it.vincenzopicone.foodball.auth.payload;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.vincenzopicone.foodball.auth.entity.Role;
import it.vincenzopicone.foodball.model.Prenotazione;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
	private String username;
    private String accessToken;
    private String tokenType = "Bearer";
    private String name;
    private String email;
    private Set<Role> roles; 
    private List<Prenotazione> prenotazione;
}
