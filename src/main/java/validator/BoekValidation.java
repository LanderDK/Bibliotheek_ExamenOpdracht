package validator;

import domain.Boek;
import domain.BoekLocatie;

import java.util.ArrayList;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BoekValidation implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Boek.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Boek boek = (Boek) target;

		if (boek.getBoekNaam() == "" || boek.getBoekNaam().isBlank() || boek.getBoekNaam().isEmpty()) {
			errors.rejectValue("boekNaam", "boekNaamNotFilledIn", null, "");
		}

		ArrayList<String> auteurs = new ArrayList<>();
		for (String auteur : boek.getAuteurs()) {
			if (auteur != "" || !auteur.isBlank() || !auteur.isEmpty()) {
				auteurs.add(auteur);
			}
			if (auteurs.isEmpty()) {
				errors.rejectValue("auteurs", "auteursIsNullOrEmpty", null, "");
			}
		}

		for (BoekLocatie bl : boek.getLocaties()) {
			if (bl.getPlaatsnaam() != "" || !bl.getPlaatsnaam().isBlank() || !bl.getPlaatsnaam().isEmpty()) {
				if (bl.getPlaatscode1() - bl.getPlaatscode2() <= 50) {
					errors.rejectValue("locaties", "verschilPlaatsCodesKleinDan50", null, "");
				}
			}
		}
	}

}