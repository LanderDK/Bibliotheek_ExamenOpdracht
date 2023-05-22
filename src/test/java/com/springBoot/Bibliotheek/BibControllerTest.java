package com.springBoot.Bibliotheek;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BibControllerTest {

	//Inject MockMvc
	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(username = "Baba", roles = {"USER"})
    @Test
    public void testHasAccessToBibPage() throws Exception {
		mockMvc.perform(get(
				"/bibliotheek/list"))
        .andExpect(status().isOk())
		.andExpect(view().name("bibPage"))
		.andExpect(model().attributeExists("user"))
		.andExpect(model().attributeExists("userListRoles"))
		.andExpect(model().attributeExists("boeken"));
    }
	
	@WithMockUser(username = "Baba", roles = {"USER"})
    @Test
    public void testNoAccessToAddPage() throws Exception {
        mockMvc.perform(get("/bibliotheek/add"))
        .andExpect(status().isForbidden());
    }
	
	@WithMockUser(username = "Lander", roles = {"ADMIN"})
    @Test
    public void testHasAccessToAddPage() throws Exception {
        mockMvc.perform(get("/bibliotheek/add"))
        .andExpect(status().isOk());
    }
	
	@WithMockUser(username = "user", roles = { "NOT_USER" })
    @Test
    public void testNoAccessWithWrongUserRole() throws Exception {
        mockMvc.perform(get("/bibliotheek/list"))
        .andExpect(status().isForbidden());
    }
	
	@WithMockUser(username = "Baba", roles = {"USER"})
    @Test
    public void testHasAccessToDetailPage() throws Exception {
		mockMvc.perform(get(
				"/bibliotheek/1"))
        .andExpect(status().isOk())
		.andExpect(view().name("detailBoekPage"))
		.andExpect(model().attributeExists("user"))
		.andExpect(model().attributeExists("userListRoles"))
		.andExpect(model().attributeExists("boek"));
    }
}
