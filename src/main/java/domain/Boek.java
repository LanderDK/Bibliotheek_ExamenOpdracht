package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQueries({ @NamedQuery(name = "Boek.findAll", query = "SELECT b FROM Boek b"),
		@NamedQuery(name = "Boek.findMeestPopulair", query = "SELECT b FROM Boek b WHERE aantalSterren > 1 ORDER BY aantalSterren DESC LIMIT 2") })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "boekNaam")
@Getter
@Setter
public class Boek implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boekID;

	private String boekNaam;

	private List<String> auteurs;

	// @Pattern(regexp = "^\\\\d{9}[\\\\d|Xx]|\\\\d{13}$", message = "invalid ISBN
	// format")
	// @Pattern(regexp = "^(\\d{13})?$", message = "Invalid ISBN format")
	private String isbn;

	// @Pattern(regexp = "^[1-9][0-9]?$", message = "must be between 1 and 99")
	@DecimalMin(value = "1", message = "must be between 1 and 99")
	@DecimalMax(value = "99", message = "must be between 1 and 99")
	private Double aankoopprijs;

	private List<BoekLocatie> locaties;

	private Integer aantalSterren;

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