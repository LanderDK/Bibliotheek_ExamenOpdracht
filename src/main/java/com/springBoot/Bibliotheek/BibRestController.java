package com.springBoot.Bibliotheek;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Boek;
import domain.BoekLocatie;
import repository.BoekLocatieRespository;
import repository.BoekRepository;

@RestController
@RequestMapping(value = "/rest")
public class BibRestController {
    
	@Autowired
	private BoekRepository br;
	@Autowired
	BoekLocatieRespository blr;
    
    @GetMapping(value = "/boek") 
    public List<Boek> getAll() {
    	return br.findAll();
    }
    
    @GetMapping(value = "/boek/id/{boekID}") 
    public Boek getBoekById(@PathVariable int boekID) {
    	return br.findByBoekID(boekID).get();
    }
    
    @GetMapping(value = "/boek/isbn/{isbn}") 
    public Boek getBoekByIsbn(@PathVariable String isbn) {
    	return br.findByIsbn(isbn).get();
    }
    
    @GetMapping(value = "/boek/naam/{boekNaam}") 
    public Boek getBoekByNaam(@PathVariable String boekNaam) {
    	return br.findByBoekNaam(boekNaam).get();
    }
    
    @GetMapping(value = "/boek/populair") 
    public List<Boek> getAllPopulairste() {
    	return br.findMeestPopulair();
    }
    
    @PostMapping(value = "/boek/create")
    public Boek createBoek(@RequestBody Boek boek) { 
    	br.save(boek);
		ArrayList<BoekLocatie> locaties = new ArrayList<>();
		for (BoekLocatie bl : boek.getLocaties()) {
			if (bl.getPlaatsnaam() != "" || !bl.getPlaatsnaam().isBlank() || !bl.getPlaatsnaam().isEmpty()) {
				locaties.add(bl);
			}
		}
		for (BoekLocatie locatie : locaties)
			blr.save(locatie);
		return boek;
    }
}
