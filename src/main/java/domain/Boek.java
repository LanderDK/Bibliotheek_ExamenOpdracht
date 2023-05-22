package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NamedQueries({ @NamedQuery(name = "Boek.findAll", query = "SELECT b FROM Boek b"),
		@NamedQuery(name = "Boek.findMeestPopulair", query = "SELECT b FROM Boek b WHERE aantalSterren > 1 ORDER BY aantalSterren DESC LIMIT 2") })
@NoArgsConstructor
@EqualsAndHashCode(of = "boekNaam")
@ToString
@Getter
@Setter
public class Boek implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boekID;

	@NotNull
	private String boekNaam;

	@NotNull
	private List<String> auteurs;

	@Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "Invalid ISBN format")
	private String isbn;

	@NotNull
	@Min(value = 1, message = "{boek.aankoopprijs.minimumValue}")
	@Max(value = 99, message = "{boek.aankoopprijs.maximumValue}")
	private Double aankoopprijs;

	@NotNull
	private List<BoekLocatie> locaties;

	@NotNull
	@Min(value = 0)
	private Integer aantalSterren;

	@NotNull
	@Pattern(regexp = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$", message = "Invalid IMG URL")
	private String img;

	public Boek(String boekNaam, ArrayList<String> auteurs, String isbn, Double aankoopprijs,
			List<BoekLocatie> locaties, Integer aantalSterren, String img) {
		this.boekNaam = boekNaam;
		this.auteurs = auteurs;
		this.isbn = isbn;
		this.aankoopprijs = aankoopprijs;
		this.locaties = locaties;
		this.aantalSterren = aantalSterren;
		this.img = img;
	}
}