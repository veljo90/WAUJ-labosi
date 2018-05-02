package hr.tvz.lisec.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import hr.tvz.lisec.data.DvoraneRepository;
import hr.tvz.lisec.data.PredavanjeRepository;
import hr.tvz.lisec.entities.Predavac;
import hr.tvz.lisec.entities.Predavanje;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(path = "/predavanja")
@SessionAttributes({"vrste", "pozicije", "listaPredavanja"})
public class PredavanjaController {
	
	private final PredavanjeRepository predavanjeRepository;
	private final DvoraneRepository dvoraneRepository;
	
	@Autowired
	public PredavanjaController(PredavanjeRepository predavanjeRepository, DvoraneRepository dvoraneRepository) {
		this.predavanjeRepository = predavanjeRepository;
		this.dvoraneRepository = dvoraneRepository;
	}

	@ModelAttribute("listaPredavanja")
	public List<Predavanje> setListaPredavanja(){
		return new ArrayList<Predavanje>();
	}
	
	@GetMapping("/novo")
	public String showForm(Model model) {
		
		log.info("Punim podatke za prikaz forme.");
		model.addAttribute("predavanje", new Predavanje());
		model.addAttribute("vrste", Predavanje.Vrsta.values());
		model.addAttribute("pozicije", Predavac.Pozicija.values());
		
		return "novoPredavanje";
	}
	
	@PostMapping("/novo")
	public String processForm(@Valid Predavanje predavanje, Errors errors, Model model) {
		
		log.info("Obrađujem predavanje:" + predavanje);
		if(errors.hasErrors()) {
			log.info("Predavanje ima grešaka. Prekidam slanje.");
			return "novoPredavanje";
		}
		
		predavanjeRepository.save(predavanje);
		
		log.info("Predavanje je spremljeno");

		@SuppressWarnings("unchecked")
		ArrayList<Predavanje> listaPredavanja = (ArrayList<Predavanje>) model.asMap().get("listaPredavanja");
		listaPredavanja.add(predavanje);
		
		return "predavanjePrihvaceno";
	}
	
	@GetMapping("/resetBrojac")
	public String resetBrojac(SessionStatus status){
		log.info("Čistim podatke u sessionu");
		status.setComplete();
		return "redirect:/predavanja/novo";
	}
	
	@GetMapping("/prikaziPredavanja")
	public String showClasses(Model model) {
		log.info("Prikazujem sva predavanja");
		
		model.addAttribute("popisPredavanja", predavanjeRepository.findAll());
		
		return "unesenaPredavanja";
	}
	
	@GetMapping("/prikaziDvorane")
	public String showClassroms(Model model) {
		log.info("Prikazujem dvorane");
		
		model.addAttribute("popisDvorana", dvoraneRepository.findAvailable());
		
		return "dvorane";
	}
}
