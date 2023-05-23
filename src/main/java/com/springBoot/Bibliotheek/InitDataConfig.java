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

		BoekLocatie bl1 = new BoekLocatie(200, 50, "Aalter");
		BoekLocatie bl2 = new BoekLocatie(150, 55, "Gent");
		BoekLocatie bl3 = new BoekLocatie(300, 50, "Antwerpen");
		BoekLocatie bl4 = new BoekLocatie(300, 55, "Leuven");
		BoekLocatie bl5 = new BoekLocatie(258, 100, "Hasselt");
		BoekLocatie bl6 = new BoekLocatie(193, 66, "Mechelen");
		BoekLocatie bl7 = new BoekLocatie(299, 224, "Brugge");
		BoekLocatie bl8 = new BoekLocatie(289, 204, "Brussel");
		BoekLocatie bl9 = new BoekLocatie(144, 54, "Oostende");
		BoekLocatie bl10 = new BoekLocatie(194, 59, "Namen");

		Boek boek1 = new Boek("De Grijze Jager - De ruïnes van Gorlan", new ArrayList<>(Arrays.asList("John Flanagan")),
				"978-3-16-148410-0", 14.99, new ArrayList<>(Arrays.asList(bl1, bl2)), 3,
				"https://media.s-bol.com/RKV3m0EmJLnR/3VPRqR/550x834.jpg");
		Boek boek2 = new Boek("WarriorCats: De wildernis in", new ArrayList<>(Arrays.asList("Erin Hunter")),
				"978-0-306-40615-7", 12.99, new ArrayList<>(Arrays.asList(bl1)), 2,
				"https://media.s-bol.com/9D7AjDgD7JvP/543x840.jpg");
		Boek boek3 = new Boek("The Talisman", new ArrayList<>(Arrays.asList("Stephen King", "Peter Straub")),
				"978-1-60309-025-4", 19.99, new ArrayList<>(Arrays.asList(bl2)), 1,
				"https://d28hgpri8am2if.cloudfront.net/book_images/onix/cvr9781501192272/the-talisman-9781501192272_hr.jpg");
		Boek boek4 = new Boek("Harry Potter and the Sorcerer's Stone", new ArrayList<>(Arrays.asList("J.K. Rowling")),
				"978-0-545-01022-1", 9.99, new ArrayList<>(Arrays.asList(bl3, bl4)), 5,
				"https://images-na.ssl-images-amazon.com/images/I/51UoqRAxwEL._SX331_BO1,204,203,200_.jpg");
		Boek boek5 = new Boek("To Kill a Mockingbird", new ArrayList<>(Arrays.asList("Harper Lee")),
				"978-0-446-31078-9", 8.99, new ArrayList<>(Arrays.asList(bl5)), 3,
				"https://upload.wikimedia.org/wikipedia/commons/4/4f/To_Kill_a_Mockingbird_%28first_edition_cover%29.jpg");
		Boek boek6 = new Boek("1984", new ArrayList<>(Arrays.asList("George Orwell")), "978-0-452-28424-1", 11.99,
				new ArrayList<>(Arrays.asList(bl6)), 2,
				"https://kbimages1-a.akamaihd.net/a5312ed2-bc80-4f4c-972b-c24dc5990bd5/353/569/90/False/george-orwell-1984-4.jpg");
		Boek boek7 = new Boek("Pride and Prejudice", new ArrayList<>(Arrays.asList("Jane Austen")), "978-1-58663-663-2",
				7.99, new ArrayList<>(Arrays.asList(bl7, bl1)), 4,
				"https://kbimages1-a.akamaihd.net/afcd8653-3b27-4423-bee9-570fb1441aed/353/569/90/False/pride-and-prejudice-71.jpg");
		Boek boek8 = new Boek("The Hobbit", new ArrayList<>(Arrays.asList("J.R.R. Tolkien")), "978-0-261-10247-1",
				10.99, new ArrayList<>(Arrays.asList(bl2, bl5)), 3,
				"https://tolkiengateway.net/w/images/e/e4/The_Hobbit_2016-facsimile.jpeg");
		Boek boek9 = new Boek("The Catcher in the Rye", new ArrayList<>(Arrays.asList("J.D. Salinger")),
				"978-0-316-76948-8", 9.99, new ArrayList<>(Arrays.asList(bl3)), 1,
				"https://upload.wikimedia.org/wikipedia/commons/8/89/The_Catcher_in_the_Rye_%281951%2C_first_edition_cover%29.jpg");
		Boek boek10 = new Boek("The Great Gatsby", new ArrayList<>(Arrays.asList("F. Scott Fitzgerald")),
				"978-0-743-29848-1", 8.99, new ArrayList<>(Arrays.asList(bl7)), 2,
				"https://kbimages1-a.akamaihd.net/c5742da9-e63f-4ed5-acb6-074fab5e3f41/353/569/90/False/the-great-gatsby-11.jpg");
		Boek boek11 = new Boek("The Lord of the Rings", new ArrayList<>(Arrays.asList("J.R.R. Tolkien")),
				"978-0-261-10248-8", 19.99, new ArrayList<>(Arrays.asList(bl10, bl1, bl9)), 5,
				"https://kbimages1-a.akamaihd.net/7a557cb3-f72a-47c3-992b-951c9566e4d4/353/569/90/False/the-fellowship-of-the-ring-the-lord-of-the-rings-book-1-1.jpg");
		Boek boek12 = new Boek("To Kill a Kingdom", new ArrayList<>(Arrays.asList("Alexandra Christo")),
				"978-1-250-15714-5", 13.99, new ArrayList<>(Arrays.asList(bl5, bl9)), 3,
				"https://kbimages1-a.akamaihd.net/fc95226d-232b-4233-8148-5f38fafa6bc5/353/569/90/False/to-kill-a-kingdom-1.jpg");
		Boek boek13 = new Boek("The Alchemist", new ArrayList<>(Arrays.asList("Paulo Coelho")), "978-0-06-231500-7",
				10.99, new ArrayList<>(Arrays.asList(bl2, bl4)), 2,
				"https://kbimages1-a.akamaihd.net/32ad8373-9cc5-4c4f-aa82-8155edbc7029/353/569/90/False/the-alchemist-a-graphic-novel.jpg");
		Boek boek14 = new Boek("The Hunger Games", new ArrayList<>(Arrays.asList("Suzanne Collins")),
				"978-0-439-02352-8", 9.99, new ArrayList<>(Arrays.asList(bl6)), 4,
				"https://kbimages1-a.akamaihd.net/fcc61f79-6dc3-4578-a49b-9628deb9ae23/353/569/90/False/the-hunger-games-hunger-games-book-one.jpg");
		Boek boek15 = new Boek("The Da Vinci Code", new ArrayList<>(Arrays.asList("Dan Brown")), "978-0-385-50420-8",
				14.99, new ArrayList<>(Arrays.asList(bl7)), 2,
				"https://kbimages1-a.akamaihd.net/3c3ebe92-cdbb-4f66-a5ec-4a0ea83e9504/353/569/90/False/the-da-vinci-code-1.jpg");
		Boek boek16 = new Boek("The Chronicles of Narnia", new ArrayList<>(Arrays.asList("C.S. Lewis")),
				"978-0-00-752809-7", 16.99, new ArrayList<>(Arrays.asList(bl10, bl5)), 3,
				"https://kbimages1-a.akamaihd.net/92cc43f2-0709-4e8e-9128-2cb2fc0c93fc/353/569/90/False/the-lion-the-witch-and-the-wardrobe-the-chronicles-of-narnia-book-2.jpg");
		Boek boek17 = new Boek("The Girl on the Train", new ArrayList<>(Arrays.asList("Paula Hawkins")),
				"978-0-8129-9868-9", 11.99, new ArrayList<>(Arrays.asList(bl5)), 1,
				"https://crimethrillergirl.files.wordpress.com/2015/01/unknown2.jpeg");
		Boek boek18 = new Boek("The Maze Runner", new ArrayList<>(Arrays.asList("James Dashner")), "978-0-385-73794-4",
				8.99, new ArrayList<>(Arrays.asList(bl6, bl4)), 2,
				"https://kbimages1-a.akamaihd.net/5f4afbce-f9bf-4c87-92b1-806c2f3c7b41/353/569/90/False/the-maze-runner-maze-runner-book-one.jpg");
		Boek boek19 = new Boek("The Fault in Our Stars", new ArrayList<>(Arrays.asList("John Green")),
				"978-0-14-134565-9", 9.99, new ArrayList<>(Arrays.asList(bl1)), 3,
				"https://upload.wikimedia.org/wikipedia/en/a/a9/The_Fault_in_Our_Stars.jpg");
		Boek boek20 = new Boek("The Book Thief", new ArrayList<>(Arrays.asList("Markus Zusak")), "978-0-375-83100-3",
				12.99, new ArrayList<>(Arrays.asList(bl1)), 2,
				"https://kbimages1-a.akamaihd.net/0979227d-2105-4042-baf9-c7f735057add/353/569/90/False/the-book-thief-9.jpg");

		Users lander = new Users("Lander", BCrypt.hashpw("TestPass1", BCrypt.gensalt(10)), new ArrayList<>(Arrays.asList(1, 2)), 5, true);
		Users baba = new Users("Baba", BCrypt.hashpw("TestPass2", BCrypt.gensalt(10)), new ArrayList<>(Arrays.asList(5, 12)), 7, true);

		boekLocatieRepository.save(bl1);
		boekLocatieRepository.save(bl2);
		boekLocatieRepository.save(bl3);
		boekLocatieRepository.save(bl4);
		boekLocatieRepository.save(bl5);
		boekLocatieRepository.save(bl6);
		boekLocatieRepository.save(bl7);
		boekLocatieRepository.save(bl8);
		boekLocatieRepository.save(bl9);
		boekLocatieRepository.save(bl10);

		boekRepository.save(boek1);
		boekRepository.save(boek2);
		boekRepository.save(boek3);
		boekRepository.save(boek4);
		boekRepository.save(boek5);
		boekRepository.save(boek6);
		boekRepository.save(boek7);
		boekRepository.save(boek8);
		boekRepository.save(boek9);
		boekRepository.save(boek10);
		boekRepository.save(boek11);
		boekRepository.save(boek12);
		boekRepository.save(boek13);
		boekRepository.save(boek14);
		boekRepository.save(boek15);
		boekRepository.save(boek16);
		boekRepository.save(boek17);
		boekRepository.save(boek18);
		boekRepository.save(boek19);
		boekRepository.save(boek20);

		userRepository.save(lander);
		userRepository.save(baba);
	}
}
