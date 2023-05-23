package com.springBoot.Bibliotheek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import perform.PerformRest;
import service.BibliotheekService;
import service.BibliotheekServiceImpl;
import validator.BoekValidation;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class BibliotheekExamenOpdrachtApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BibliotheekExamenOpdrachtApplication.class, args);
		try {
			new PerformRest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/login");
		registry.addViewController("/403").setViewName("403");
	}

	@Bean
	BoekValidation bv() {
		return new BoekValidation();
	}
}
