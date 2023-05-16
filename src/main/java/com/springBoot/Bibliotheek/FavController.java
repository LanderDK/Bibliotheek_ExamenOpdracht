package com.springBoot.Bibliotheek;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import domain.Boek;
import domain.Users;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import repository.BoekRepository;
import repository.UserRepository;

@Slf4j
@Controller
@RequestMapping("/favorieten")
public class FavController {

	@Autowired
	private BoekRepository br;
	@Autowired
	private UserRepository ur;
	
	@GetMapping(value = "/add/{id}")
    public String addFav(@PathVariable("id") Integer boekID, Model model, Authentication authentication) {
		log.info("Add fav");
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		Optional<Users> user = ur.findByUsername(username);
		Users u = null;
		if (user.isPresent())
			u = user.get();
		List<String> listRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		model.addAttribute("user", u);
		model.addAttribute("userListRoles", listRoles);
		
		//check als je max aantal favorieten hebt bereikt --> NIET COMPLEET -> mooie melding tonen
		if (u.getFavorieten().size() == u.getMaxAantalFavs())
			return "redirect:/bibliotheek/list";
		
		List<Integer> favs = u.getFavorieten();
		favs.add(boekID);
		u.setFavorieten(favs);
		ur.save(u);
		
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

		return "redirect:/bibliotheek/list";
    }
	
	@GetMapping(value = "/remove/{id}")
    public String removeFav(@PathVariable("id") Integer boekID, Model model, Authentication authentication) {
		log.info("Remove fav");
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		Optional<Users> user = ur.findByUsername(username);
		Users u = null;
		if (user.isPresent())
			u = user.get();
		List<String> listRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		model.addAttribute("user", u);
		model.addAttribute("userListRoles", listRoles);
		
		List<Integer> favs = u.getFavorieten();
		favs.remove(boekID);
		u.setFavorieten(favs);
		ur.save(u);
		
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
        
		return "redirect:/bibliotheek/list";
    }
}
