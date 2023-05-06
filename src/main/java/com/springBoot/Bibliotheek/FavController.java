package com.springBoot.Bibliotheek;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import domain.Account;
import domain.Boek;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import repository.AccountRepository;
import repository.BoekRepository;

@Slf4j
@Controller
@RequestMapping("/favorieten")
public class FavController {

	@Autowired
	private BoekRepository br;
	@Autowired
	private AccountRepository ar;
	
	@GetMapping(value = "/add/{id}")
    public String addFav(@PathVariable("id") Integer boekID, Model model, HttpSession session) {
		log.info("Add fav");
		
		Account account = (Account) session.getAttribute("account");
		
		if (account == null)
			return "redirect:/login";
		
		//check als je max aantal favorieten hebt bereikt --> NIET COMPLEET -> mooie melding tonen
		if (account.getFavorieten().size() == account.getMaxAantalFavs())
			return "redirect:/bibliotheek/list";
		
		List<Integer> favs = account.getFavorieten();
		favs.add(boekID);
		account.setFavorieten(favs);
		ar.save(account);
		
		Boek b;
		Optional<Boek> boek = br.findByBoekID(boekID);
        if (!boek.isPresent()) {
			return "redirect:/bibliotheek/list";
		}
        else {
        	b = boek.get();
        	b.setAantalSterren(b.getAantalSterren() + 1);
        	br.save(b);
        }
		
		model.addAttribute("account", account);
		return "redirect:/bibliotheek/list";
    }
	
	@GetMapping(value = "/remove/{id}")
    public String removeFav(@PathVariable("id") Integer boekID, Model model, HttpSession session) {
		log.info("Remove fav");
		
		Account account = (Account) session.getAttribute("account");
		
		if (account == null)
			return "redirect:/login";
		
		List<Integer> favs = account.getFavorieten();
		favs.remove(boekID);
		account.setFavorieten(favs);
		ar.save(account);
		
		Boek b;
		Optional<Boek> boek = br.findByBoekID(boekID);
        if (!boek.isPresent()) {
			return "redirect:/bibliotheek/list";
		}
        else {
        	b = boek.get();
        	b.setAantalSterren(b.getAantalSterren() - 1);
        	br.save(b);
        }
		
		model.addAttribute("account", account);
		return "redirect:/bibliotheek/list";
    }
}
