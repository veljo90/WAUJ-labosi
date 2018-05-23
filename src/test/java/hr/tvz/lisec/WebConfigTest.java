package hr.tvz.lisec;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class WebConfigTest {

private MockMvc mockMvc;
	
	@Autowired
	WebConfigTest(MockMvc mockMvc){
		this.mockMvc = mockMvc;
	}
	
	@Test
	void testShowHomePage() throws Exception{
		this.mockMvc
			.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"));
	}
	
	@Test
	void testShowLoginPage() throws Exception{
		this.mockMvc
			.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("login"));
	}
	
	@Test
	void testLoginAdmin() throws Exception{
		this.mockMvc
			.perform(post("/login")
					.param("username", "admin")
					.param("password", "adminpass")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.with(csrf()))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/predavanja/novo"));
	}
	
	@Test
	void testLoginUser() throws Exception{
		this.mockMvc
			.perform(post("/login")
					.param("username", "student")
					.param("password", "studentpass")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.with(csrf()))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/predavanja/novo"));
	}
	
	@Test
	void testLoginInvalidPassword() throws Exception{
		this.mockMvc
			.perform(post("/login")
					.param("username", "user")
					.param("password", "invalidPassword")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.with(csrf()))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/login?error"));
	}

}
