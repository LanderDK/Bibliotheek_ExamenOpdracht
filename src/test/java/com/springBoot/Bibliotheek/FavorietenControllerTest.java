package com.springBoot.Bibliotheek;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import domain.Boek;
import domain.BoekLocatie;
import repository.BoekRepository;

@SpringBootTest
@AutoConfigureMockMvc
class FavorietenControllerTest {

	//Inject MockMvc
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoekRepository br;

    @WithMockUser(username = "Baba", roles = { "USER" })
    @Test
    public void testAddFavorite() throws Exception {
		BoekLocatie bl1 = new BoekLocatie(200, 50, "Aalter");
        Boek b = new Boek();
        b.setIsbn("978-0-439-02352-8");
        b.setBoekNaam("Boek");
        b.setAankoopprijs(9.99);
        b.setImg("https://tolkiengateway.net/w/images/e/e4/The_Hobbit_2016-facsimile.jpeg");
        b.setAantalSterren(1);
        b.setAuteurs(new ArrayList<>(Arrays.asList("Test")));
        b.setLocaties(new ArrayList<>(Arrays.asList(bl1)));
        br.save(b);

        mockMvc.perform(get("/favorieten/add/{id}", b.getBoekID()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/bibliotheek/list"));
    }

    @WithMockUser(username = "Baba", roles = { "USER" })
    @Test
    public void testRemoveFavorite() throws Exception {
    	BoekLocatie bl1 = new BoekLocatie(200, 50, "Aalter");
        Boek b = new Boek();
        b.setIsbn("978-0-439-02352-8");
        b.setBoekNaam("Boek");
        b.setAankoopprijs(9.99);
        b.setImg("https://tolkiengateway.net/w/images/e/e4/The_Hobbit_2016-facsimile.jpeg");
        b.setAantalSterren(1);
        b.setAuteurs(new ArrayList<>(Arrays.asList("Test")));
        b.setLocaties(new ArrayList<>(Arrays.asList(bl1)));
        br.save(b);

        mockMvc.perform(get("/favorieten/remove/{id}", b.getBoekID()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/bibliotheek/list"));
    }
}
