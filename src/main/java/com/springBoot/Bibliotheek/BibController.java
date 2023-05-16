package com.springBoot.Bibliotheek;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Boek;
import domain.BoekLocatie;
import domain.Users;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import repository.BoekLocatieRespository;
import repository.BoekRepository;
import repository.UserRepository;
import validator.BoekValidation;

@Slf4j
@Controller
@RequestMapping("/bibliotheek")
public class BibController {

	@Autowired
	private BoekRepository br;
	@Autowired
	private UserRepository ur;
	@Autowired
	private BoekValidation bv;
	@Autowired
	BoekLocatieRespository blr;

	@GetMapping(value = "/list")
	public String showBibPage(Model model, Authentication authentication) {
		log.info("Get bib");

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		Optional<Users> user = ur.findByUsername(username);
		Users u = null;
		if (user.isPresent())
			u = user.get();
		List<String> listRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		model.addAttribute("user", u);
		model.addAttribute("userListRoles", listRoles);
		model.addAttribute("boeken", br.findAll());

		return "bibPage";
	}

	@GetMapping(value = "/{id}")
	public String show(@PathVariable("id") Integer boekID, Model model, Authentication authentication) {
		log.info("Get boek");

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		Optional<Users> user = ur.findByUsername(username);
		Users u = null;
		if (user.isPresent())
			u = user.get();
		List<String> listRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		model.addAttribute("user", u);
		model.addAttribute("userListRoles", listRoles);

		Boek b;
		Optional<Boek> boek = br.findByBoekID(boekID);
		if (!boek.isPresent()) {
			return "redirect:/bibliotheek/list";
		} else
			b = boek.get();
		model.addAttribute("boek", b);
		return "detailBoekPage";
	}

	@GetMapping(value = "/add")
	public String showAddBoekPage(Model model, Authentication authentication) {
		log.info("Add boek");

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		Optional<Users> user = ur.findByUsername(username);
		Users u = null;
		if (user.isPresent())
			u = user.get();
		List<String> listRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		model.addAttribute("user", u);
		model.addAttribute("userListRoles", listRoles);
		model.addAttribute(new Boek());

		return "addBoekPage";
	}

	@PostMapping(value = "/add")
	public String onSubmit(@Valid Boek boek, BindingResult result, Model model, Authentication authentication) {
		log.info("Post boek");

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		Optional<Users> user = ur.findByUsername(username);
		Users u = null;
		if (user.isPresent())
			u = user.get();
		List<String> listRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		model.addAttribute("user", u);
		model.addAttribute("userListRoles", listRoles);

		// validate
		bv.validate(boek, result);
		if (result.hasErrors())
			return "addBoekPage";

		br.save(boek);
		ArrayList<BoekLocatie> locaties = new ArrayList<>();
		for (BoekLocatie bl : boek.getLocaties()) {
			if (bl.getPlaatsnaam() != "" || !bl.getPlaatsnaam().isBlank() || !bl.getPlaatsnaam().isEmpty()) {
				locaties.add(bl);
			}
		}
		for (BoekLocatie locatie : locaties)
			blr.save(locatie);

		return "redirect:/bibliotheek/list";
	}

	@GetMapping(value = "/populair")
	public String showBibPopulairPage(Model model, Authentication authentication) {
		log.info("Get bib populair");

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		Optional<Users> user = ur.findByUsername(username);
		Users u = null;
		if (user.isPresent())
			u = user.get();
		List<String> listRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		model.addAttribute("user", u);
		model.addAttribute("userListRoles", listRoles);

		model.addAttribute("boeken", br.findMeestPopulair());
		return "bibPopulairPage";
	}
}
