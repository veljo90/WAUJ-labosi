package hr.tvz.lisec.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import hr.tvz.lisec.entities.Predavac;
import hr.tvz.lisec.entities.Predavanje;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PredavanjaControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	PredavanjaControllerTest(MockMvc mockMvc){
		this.mockMvc = mockMvc;
	}
	
	@Test
	public void testPrikaziSvaPredavanjaAdmin() throws Exception {
		this.mockMvc
			.perform(get("/predavanja/prikaziPredavanja").with(user("admin").password("adminpass").roles("USER", "ADMIN")))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("popisPredavanja"))
			.andExpect(view().name("unesenaPredavanja"));
	}
	
	@Test
	public void testPrikaziSvaPredavanjaUser() throws Exception {
		this.mockMvc
			.perform(get("/predavanja/prikaziPredavanja").with(user("user").password("userpass").roles("USER")))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("popisPredavanja"))
			.andExpect(view().name("unesenaPredavanja"));
	}
	
	@Test
	public void testPrikaziFormuZaUnosPredavanjaAdmin() throws Exception {
		this.mockMvc
			.perform(get("/predavanja/novo").with(user("admin").password("adminpass").roles("USER", "ADMIN")))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("predavanje"))
			.andExpect(model().attributeExists("vrste"))
			.andExpect(model().attributeExists("pozicije"))
			.andExpect(view().name("novoPredavanje"));
	}
	
	@Test
	public void testPrikaziFormuZaUnosPredavanjaUser() throws Exception {
		this.mockMvc
			.perform(get("/predavanja/novo").with(user("user").password("userpass").roles("USER")))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("predavanje"))
			.andExpect(model().attributeExists("vrste"))
			.andExpect(model().attributeExists("pozicije"))
			.andExpect(view().name("novoPredavanje"));
	}
	
	@Test
	public void testPredajPogresnoPredavanje() throws Exception {
		this.mockMvc
			.perform(post("/predavanja/novo")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.with(csrf())
					.with(user("admin").password("adminpass").roles("USER", "ADMIN")))
			.andExpect(status().isOk())
			.andExpect(view().name("novoPredavanje"));
	}
	
	@Test
	public void testPredajPravilnoPredavanje() throws Exception {
		this.mockMvc
			.perform(post("/predavanja/novo")
					.param("tema", "Spring")
					.param("sadrzaj", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
					.param("vrsta", Predavanje.Vrsta.PREZENTACIJA.toString())
					.param("predavac.ime", "Velimir")
					.param("predavac.pozicija", Predavac.Pozicija.MID.toString())
					.param("objavljeno", Boolean.TRUE.toString())
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.with(csrf())
					.with(user("admin").password("adminpass").roles("USER", "ADMIN")))
			.andExpect(status().isOk())
			.andExpect(view().name("predavanjePrihvaceno"));
	}
	
	@Test
	public void testResetBrojaca() throws Exception {
		this.mockMvc
			.perform(get("/predavanja/resetBrojac")
					.with(user("admin").password("adminpass").roles("USER", "ADMIN")))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/predavanja/novo"));
	}
	
	@Test
	public void testPrikaziPretraziPredavanja() throws Exception {
		this.mockMvc
			.perform(get("/predavanja/pretraziPredavanja")
					.with(user("admin").password("adminpass").roles("USER", "ADMIN")))
			.andExpect(status().isOk())
			.andExpect(view().name("pretragaPredavanja"));
	}
}
