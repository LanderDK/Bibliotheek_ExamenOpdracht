package domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "plaatsnaam")
public class BoekLocatie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int locatieID;

	@Min(50)
	@Max(300)
	private int plaatscode1;

	@Min(50)
	@Max(300)
	private int plaatscode2;

	@Pattern(regexp = "^[a-zA-Z]+$")
	private String plaatsnaam;

	public BoekLocatie(int plaatscode1, int plaatscode2, String plaatsnaam) {
		this.plaatscode1 = plaatscode1;
		this.plaatscode2 = plaatscode2;
		this.plaatsnaam = plaatsnaam;
	}

}