package perform;

import org.springframework.web.reactive.function.client.WebClient;

import domain.Boek;
import reactor.core.publisher.Mono;

public class PerformRest {

	private final String SERVER_URI = "http://localhost:8081/rest";
	private WebClient webClient = WebClient.create();

	public PerformRest() throws Exception {
		System.out.println("\n------- GET 978-0-261-10247-1 ------- ");
		getBoek("978-0-261-10247-1");
		System.out.println("\n------- GET LIST AUTEUR ------- ");
		getBoekListAuteur("J.R.R. Tolkien");
		System.out.println("\n------- GET ALL -------");
		getAllBooks();

	}

	private void getBoekListAuteur(String auteur) {
		String uri = SERVER_URI + "/boek/auteur/" + auteur;
		webClient.get().uri(uri).retrieve().bodyToFlux(Boek.class).doOnNext(this::printBoekData).blockLast();

	}

	private void getAllBooks() {
		webClient.get().uri(SERVER_URI + "/boeken").retrieve().bodyToFlux(Boek.class).flatMap(boek -> {
			printBoekData(boek);
			return Mono.empty();
		}).blockLast();
	}

	private void getABoek(String uri) {
		webClient.get().uri(uri).retrieve().bodyToMono(Boek.class).doOnSuccess(boek -> printBoekData(boek)).block();
	}

	private void getBoek(String isbn) {
		getABoek(SERVER_URI + "/boek/" + isbn);
	}

	private void printBoekData(Boek boek) {
		if (boek != null) {
			System.out.printf("ISBN=%s, Naam=%s, Aantalsterren=%s, Aankoopprijs=%s, Imgurl=%s, Auteurs=%s, Locaties=%s",
					boek.getIsbn(), boek.getBoekNaam(), boek.getAantalSterren(), boek.getAankoopprijs(), boek.getImg(),
					boek.getAuteurs(), boek.getLocaties());
		} else {
			System.out.println("Boek is null");
		}
	}

}
