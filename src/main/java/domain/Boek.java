package domain;

import java.util.List;
import java.util.Map;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor
public class Boek {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private @NotNull @NotBlank String naam;
	
	private @NotNull @NotBlank List<String> auteurs;
	
	@Pattern(regexp = "^\\\\d{9}[\\\\d|Xx]|\\\\d{13}$", message = "invalid ISBN format")
	private String isbn;
	
	@Pattern(regexp = "^[1-9][0-9]?$", message = "must be between 1 and 99")
	private Double aankoopprijs;
	
	private Map<Integer, String> locaties;
	
	private int aantalSterren;
}