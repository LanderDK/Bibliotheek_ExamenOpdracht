package com.springBoot.Bibliotheek;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import domain.Boek;
import domain.BoekLocatie;
import exceptions.BoekNotFoundException;
import repository.BoekRepository;

@SpringBootTest
@AutoConfigureMockMvc
class BoekRestMockTest {

	@Mock
	private BoekRepository mock;

	private BibRestController controller;
	private MockMvc mockMvc;

	private final BoekLocatie BL = new BoekLocatie(200, 50, "Aalter");
	private final String ISBNNUMMER = "978-0-261-10247-1";
	private final String BOEK_NAAM = "The Hobbit";
	private List<String> AUTEURS = new ArrayList<>(Arrays.asList("Test"));
	private List<BoekLocatie> LOCATIES = new ArrayList<>(Arrays.asList(BL));
	private final int AANTALSTERREN = 3;
	private final double AANKOOPPRIJS = 10.99;
	private final String IMG = "https://tolkiengateway.net/w/images/e/e4/The_Hobbit_2016-facsimile.jpeg";

	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		controller = new BibRestController();
		mockMvc = standaloneSetup(controller).build();
		ReflectionTestUtils.setField(controller, "boekRepository", mock);
	}

	private Optional<Boek> aBoek(String ISBNnummer, String boekNaam, List<String> auteurs, double aankoopprijs,
			int aantalSterren, List<BoekLocatie> locaties, String img) {
		Boek b = new Boek();
		b.setIsbn(ISBNnummer);
		b.setBoekNaam(boekNaam);
		b.setAuteurs(auteurs);
		b.setAantalSterren(aantalSterren);
		b.setAankoopprijs(aankoopprijs);
		b.setLocaties(locaties);
		b.setImg(img);
		return Optional.of(b);
	}

	private List<Boek> aBoekList(String ISBNnummer, String boekNaam, List<String> auteurs, double aankoopprijs,
			int aantalSterren, List<BoekLocatie> locaties, String img) {
		Boek b = new Boek();
		b.setIsbn(ISBNnummer);
		b.setBoekNaam(boekNaam);
		b.setAuteurs(auteurs);
		b.setAantalSterren(aantalSterren);
		b.setAankoopprijs(aankoopprijs);
		b.setLocaties(locaties);
		b.setImg(img);
		List<Boek> boekList = new ArrayList<>();
		boekList.add(b);
		return boekList;
	}

	private void performRest(String uri) throws Exception {
		mockMvc.perform(get(uri)).andExpect(status().isOk()).andExpect(jsonPath("$.isbnnummer").value(ISBNNUMMER))
				.andExpect(jsonPath("$.boek_naam").value(BOEK_NAAM))
				.andExpect(jsonPath("$.auteurs[0].voornaam").value("Lucas"))
				.andExpect(jsonPath("$.auteurs[0].achternaam").value("Thomas"))
				.andExpect(jsonPath("$.aantalsterren").value(AANTALSTERREN))
				.andExpect(jsonPath("$.aankoopPrijs").value(AANKOOPPRIJS))
				.andExpect(jsonPath("$.locaties[0].plaatscode1").value(200))
				.andExpect(jsonPath("$.locaties[0].plaatscode2").value(50))
				.andExpect(jsonPath("$.locaties[0].plaatsnaam").value("Antwerpen"))
				.andExpect(jsonPath("$.locaties[1].plaatscode1").value(100))
				.andExpect(jsonPath("$.locaties[1].plaatscode2").value(44))
				.andExpect(jsonPath("$.locaties[1].plaatsnaam").value("Brugge"))
				.andExpect(jsonPath("$.img").value(IMG));
	}

	private void performRestForArray(String uri) throws Exception {
		mockMvc.perform(get(uri)).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$[0].isbnnummer").value(ISBNNUMMER))
				.andExpect(jsonPath("$[0].boek_naam").value(BOEK_NAAM))
				.andExpect(jsonPath("$[0].auteurs[0]").value("Lucas"))
				.andExpect(jsonPath("$[0].aantalsterren").value(AANTALSTERREN))
				.andExpect(jsonPath("$[0].aankoopPrijs").value(AANKOOPPRIJS))
				.andExpect(jsonPath("$[0].locaties[0].plaatscode1").value(200))
				.andExpect(jsonPath("$[0].locaties[0].plaatscode2").value(50))
				.andExpect(jsonPath("$[0].locaties[0].plaatsnaam").value("Antwerpen"))
				.andExpect(jsonPath("$[0].locaties[1].plaatscode1").value(100))
				.andExpect(jsonPath("$[0].locaties[1].plaatscode2").value(44))
				.andExpect(jsonPath("$[0].locaties[1].plaatsnaam").value("Brugge"))
				.andExpect(jsonPath("$[0].img").value(IMG));
	}

	@Test
	public void testGetBoek_isOk() throws Exception {
		AUTEURS.add("Lucas");
		LOCATIES.add(new BoekLocatie(200, 50, "Antwerpen"));
		LOCATIES.add(new BoekLocatie(100, 44, "Brugge"));
		Mockito.when(mock.findByIsbn(ISBNNUMMER))
				.thenReturn(aBoek(ISBNNUMMER, BOEK_NAAM, AUTEURS, AANKOOPPRIJS, AANTALSTERREN, LOCATIES, IMG));
		performRest("/rest/boek/" + ISBNNUMMER);
		Mockito.verify(mock).findByIsbn(ISBNNUMMER);
	}

	@Test
	public void testGetBoek_notFound() throws Exception {
		Mockito.when(mock.findByIsbn(ISBNNUMMER)).thenThrow(new BoekNotFoundException(ISBNNUMMER));

		Exception exception = assertThrows(Exception.class, () -> {
			mockMvc.perform(get("/rest/boek/" + ISBNNUMMER)).andReturn();
		});

		assertTrue(exception.getCause() instanceof BoekNotFoundException);

		Mockito.verify(mock).findByIsbn(ISBNNUMMER);
	}

	@Test
	public void testGetAllBoeken_isOk() throws Exception {
		AUTEURS.add("Lucas");
		LOCATIES.add(new BoekLocatie(200, 50, "Antwerpen"));
		LOCATIES.add(new BoekLocatie(100, 44, "Brugge"));
		List<Boek> boeken = new ArrayList<>();
		boeken.add(aBoek(ISBNNUMMER, BOEK_NAAM, AUTEURS, AANKOOPPRIJS, AANTALSTERREN, LOCATIES, IMG).get());
		Mockito.when(mock.findAll()).thenReturn(boeken);

		mockMvc.perform(get("/rest/boeken")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].isbnnummer").value(ISBNNUMMER))
				.andExpect(jsonPath("$.[0].boek_naam").value(BOEK_NAAM))
				.andExpect(jsonPath("$.[0].auteurs[0]").value("Lucas"))
				.andExpect(jsonPath("$.[0].aantalsterren").value(AANTALSTERREN))
				.andExpect(jsonPath("$.[0].aankoopPrijs").value(AANKOOPPRIJS))
				.andExpect(jsonPath("$.[0].locaties[0].plaatscode1").value(200))
				.andExpect(jsonPath("$.[0].locaties[0].plaatscode2").value(50))
				.andExpect(jsonPath("$.[0].locaties[0].plaatsnaam").value("Antwerpen"))
				.andExpect(jsonPath("$.[0].locaties[1].plaatscode1").value(100))
				.andExpect(jsonPath("$.[0].locaties[1].plaatscode2").value(44))
				.andExpect(jsonPath("$.[0].locaties[1].plaatsnaam").value("Brugge"))
				.andExpect(jsonPath("$.[0].img").value(IMG));

		Mockito.verify(mock).findAll();
	}

	@Test
	public void testGetAllBoekenFromAuteur_isOk() throws Exception {
		AUTEURS.add("Lucas");
		LOCATIES.add(new BoekLocatie(200, 50, "Antwerpen"));
		LOCATIES.add(new BoekLocatie(100, 44, "Brugge"));
		Mockito.when(mock.findAllOfAuteur("Lucas"))
				.thenReturn(aBoekList(ISBNNUMMER, BOEK_NAAM, AUTEURS, AANKOOPPRIJS, AANTALSTERREN, LOCATIES, IMG));
		performRestForArray("/rest/boek/auteur/" + "Lucas");
		Mockito.verify(mock).findAllOfAuteur("Lucas");

	}

	@Test
	public void testGetAllBoekenFromAuteur_notFound() throws Exception {
		Mockito.when(mock.findAllOfAuteur("Bart")).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/rest/boek/auteur/{auteur}", "Bart")).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$").isEmpty());

		Mockito.verify(mock).findAllOfAuteur("Bart");
	}

}
