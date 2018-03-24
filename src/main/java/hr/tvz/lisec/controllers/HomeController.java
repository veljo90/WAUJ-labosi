package hr.tvz.lisec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping
public class HomeController {
	
	@GetMapping
	public String showHome() {
		log.info("Prikazujem home stranicu");
		return "home";
	}
}
