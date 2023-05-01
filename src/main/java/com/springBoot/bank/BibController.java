package com.springBoot.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Account;
import domain.BibBoekLookup;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/bibliotheek")
public class BibController {
	
	@Autowired
	private BibBoekLookup bbl;
	
	@GetMapping
	public String showBibPage(Model model, HttpSession session) {
		log.info("Get bib");
		model.addAttribute("boeken", bbl.getAllBoeken());
	    Account account = (Account) session.getAttribute("account");
	    model.addAttribute("account", account);
	    if (account != null) {
	        // display the biblioteek page
	        return "bibPage";
	    } else {
	        // redirect to the login page
	        return "redirect:/login";
	    }
	}
}
