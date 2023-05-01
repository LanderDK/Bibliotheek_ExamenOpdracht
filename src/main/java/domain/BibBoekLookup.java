package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibBoekLookup {
	private List<Boek> boeken = new ArrayList<>();

	public BibBoekLookup() {
		boeken.add(new Boek(1, "De Grijze Jager - Boek 1", new ArrayList<>(Arrays.asList("John Flanagan")),
				"978-3-16-148410-0", 14.99, new HashMap<>(Map.of(9880, "Aalter", 9000, "Gent")), 5));
		boeken.add(new Boek(2, "Warrior Cats - Boek 1", new ArrayList<>(Arrays.asList("Erin Hunter")),
				"978-0-306-40615-7", 12.99, new HashMap<>(Map.of(9880, "Aalter", 9000, "Gent")), 4));
		boeken.add(new Boek(3, "The Talisman", new ArrayList<>(Arrays.asList("Stephen King", "Peter Straub")),
				"978-1-60309-025-4", 19.99, new HashMap<>(Map.of(9880, "Aalter", 9000, "Gent")), 5));
	}

	public List<Boek> getAllBoeken() {
		return boeken;
	}
	
	public Boek getBoekMetId(int id) {
		return (Boek) boeken.stream().filter(b -> b.getId() == id);
	}
}
