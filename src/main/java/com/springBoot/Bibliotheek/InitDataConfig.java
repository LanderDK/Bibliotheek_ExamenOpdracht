package com.springBoot.Bibliotheek;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import domain.Boek;
import domain.BoekLocatie;
import domain.Users;
import repository.BoekLocatieRespository;
import repository.BoekRepository;
import repository.UserRepository;

@Component
public class InitDataConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BoekRepository boekRepository;
	@Autowired
	private BoekLocatieRespository boekLocatieRepository;

	@Override
	public void run(String... args) {

		BoekLocatie bl1 = new BoekLocatie(55, 299, "Aalter");
		BoekLocatie bl2 = new BoekLocatie(154, 199, "Gent");

		Boek boek1 = new Boek("De Grijze Jager - De ruïnes van Gorlan", new ArrayList<>(Arrays.asList("John Flanagan")),
				"978-3-16-148410-0", 14.99, new ArrayList<>(Arrays.asList(bl1, bl2)), 3,
				"https://media.s-bol.com/RKV3m0EmJLnR/3VPRqR/550x834.jpg");
		Boek boek2 = new Boek("WarriorCats: De wildernis in", new ArrayList<>(Arrays.asList("Erin Hunter")),
				"978-0-306-40615-7", 12.99, new ArrayList<>(Arrays.asList(bl1)), 2,
				"https://media.s-bol.com/9D7AjDgD7JvP/543x840.jpg");
		Boek boek3 = new Boek("The Talisman", new ArrayList<>(Arrays.asList("Stephen King", "Peter Straub")),
				"978-1-60309-025-4", 19.99, new ArrayList<>(Arrays.asList(bl2)), 1,
				"https://d28hgpri8am2if.cloudfront.net/book_images/onix/cvr9781501192272/the-talisman-9781501192272_hr.jpg");

		Users lander = new Users("Lander", BCrypt.hashpw("TestPass1", BCrypt.gensalt(10)), new ArrayList<>(), 2, true);
		Users baba = new Users("Baba", BCrypt.hashpw("TestPass2", BCrypt.gensalt(10)), new ArrayList<>(), 3, true);

		boekLocatieRepository.save(bl1);
		boekLocatieRepository.save(bl2);

		boekRepository.save(boek1);
		boekRepository.save(boek2);
		boekRepository.save(boek3);

		userRepository.save(lander);
		userRepository.save(baba);
	}
}
