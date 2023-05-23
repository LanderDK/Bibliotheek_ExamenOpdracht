package com.springBoot.Bibliotheek;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Boek;
import exceptions.BoekNotFoundException;
import repository.BoekLocatieRespository;
import repository.BoekRepository;

@RestController
@RequestMapping(value = "/rest")
public class BibRestController {

	@Autowired
	private BoekRepository br;
	@Autowired
	BoekLocatieRespository blr;

	@GetMapping(value = "/boeken")
	public List<Boek> getAll() {
		return br.findAll();
	}

	@GetMapping(value = "/boek/{isbn}")
	public Boek getBoekByIsbn(@PathVariable String isbn) {
		Optional<Boek> b = br.findByIsbn(isbn);
		if (b.isPresent()) {
			return b.get();
		} else {
			throw new BoekNotFoundException(isbn);
		}
	}

//  http://localhost:8081/rest/boek/auteur/Erin Hunter
//	http://localhost:8081/rest/boek/auteur/J.R.R. Tolkien
	@GetMapping(value = "/boek/auteur/{auteur}")
	public List<Boek> getBoekenOfAuteur(@PathVariable String auteur) {
		return br.findAllOfAuteur(auteur);
	}
}
