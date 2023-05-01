package com.springBoot.bank;

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

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String showLoginPage(Model model, HttpSession session) {
		log.info("Get login");
		session.setAttribute("account", null);
		model.addAttribute("account", new Account());
		return "loginPage";
	}

	@PostMapping
	public String onSubmit(@Valid Account account, BindingResult result, Model model, HttpSession session) {
		log.info("Post login");
		// validate
		if (result.hasErrors())
			return "loginFailedPage";

		account = new Account("123", account.getUsername(), account.getPassword(), "Admin");
		Account acc = new Account("123", account.getUsername(), account.getPassword(), "User");
		if (!account.getUsername().equals("Lander") || !account.getPassword().equals("P@ss"))
			return "loginFailedPage";
		
		session.setAttribute("account", account);
		return "redirect:/bibliotheek";
	}
}
