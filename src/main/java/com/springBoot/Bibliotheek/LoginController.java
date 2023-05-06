package com.springBoot.Bibliotheek;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Account;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import repository.AccountRepository;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	AccountRepository ar;

	@GetMapping
	public String showLoginPage(HttpSession session) {
		log.info("Get login");
		session.setAttribute("account", null);
		return "loginPage";
	}

	@PostMapping
	public String onSubmit(@Valid Account account, BindingResult result, Model model, HttpSession session) {
		log.info("Post login");
		Account acc;
		
		// validate
		if (result.hasErrors())
			return "loginPage";

		Optional<Account> accOptional = ar.findByUsernameAndPassword(account.getUsername(), account.getPassword());
		if (!accOptional.isPresent())
			return "loginPage";
		else
			acc = accOptional.get();

		session.setAttribute("account", acc);
		return "redirect:/bibliotheek/list";
	}
}
