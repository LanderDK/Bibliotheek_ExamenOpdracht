package com.springBoot.Bibliotheek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import service.BibliotheekService;
import service.BibliotheekServiceImpl;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class BibliotheekExamenOpdrachtApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BibliotheekExamenOpdrachtApplication.class, args);
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/login");
	}
	
	@Bean
	BibliotheekService bs() {
		return new BibliotheekServiceImpl();
	}
}
