package com.springBoot.Bibliotheek;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Account;
import domain.Boek;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import repository.BoekRepository;

@Slf4j
@Controller
@RequestMapping("/bibliotheek")
public class BibController {

	@Autowired
	private BoekRepository br;

	@GetMapping(value = "/list")
	public String showBibPage(Model model, HttpSession session) {
		log.info("Get bib");
		Account account = (Account) session.getAttribute("account");
		if (account == null)
			return "redirect:/login";
		model.addAttribute("account", account);
		model.addAttribute("boeken", br.findAll());
		return "bibPage";
	}
	
	@GetMapping(value = "/{id}")
    public String show(@PathVariable("id") Integer boekID, Model model, HttpSession session) {
		log.info("Get boek");
		
		Account account = (Account) session.getAttribute("account");
		if (account == null)
			return "redirect:/login";
		model.addAttribute("account", account);
		
		Boek b;
		Optional<Boek> boek = br.findByBoekID(boekID);
        if (!boek.isPresent()) {
			return "redirect:/bibliotheek/list";
		}
        else
        	b = boek.get();
        model.addAttribute("boek", b);
        return "detailBoekPage";
    }

	@GetMapping(value = "/add")
	public String showAddBoekPage(Model model, HttpSession session) {
		log.info("Add boek");
		Account account = (Account) session.getAttribute("account");
		if (account == null)
			return "redirect:/login";
		if (!account.getRole().equals("Admin"))
			return "redirect:/bibliotheek/list";
		model.addAttribute("account", account);
		return "addBoekPage";
	}
	
	@PostMapping(value = "/add")
	public String onSubmit(@Valid Boek boek, BindingResult result, Model model, HttpSession session) {
		log.info("Post boek");
		
		// validate
		System.out.println(result);
		if (result.hasErrors())
			return "redirect:/bibliotheek/list";
		
		System.out.println(boek.getLocaties());
		br.save(boek);
		return "redirect:/bibliotheek/list";
	}
	
	@GetMapping(value = "/populair")
	public String showBibPopulairPage(Model model, HttpSession session) {
		log.info("Get bib populair");
		Account account = (Account) session.getAttribute("account");
		if (account == null)
			return "redirect:/login";
		model.addAttribute("account", account);
		model.addAttribute("boeken", br.findMeestPopulair());
		return "bibPopulairPage";
	}
}
