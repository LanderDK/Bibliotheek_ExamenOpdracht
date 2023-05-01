package com.springBoot.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import domain.BibBoekLookup;

@SpringBootApplication
public class BibliotheekExamenOpdrachtApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BibliotheekExamenOpdrachtApplication.class, args);
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/login");
	}
	
	@Bean
	BibBoekLookup bbl() {
		return new BibBoekLookup();
	}
}
