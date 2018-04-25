package hr.tvz.lisec.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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
	
	@Autowired
	public PredavanjaController(PredavanjeRepository predavanjeRepository) {
		this.predavanjeRepository = predavanjeRepository;
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
	
	@DeleteMapping("/delete/{id}")
	public String deletePredavanje(@PathVariable("id") long id) {
		predavanjeRepository.delete(id);
		return "redirect:/predavanja/prikaziPredavanja";
	}
	
	@GetMapping("/update/{id}")
	public String updatePredavanjeForm(@PathVariable("id") long id, Model model) {
		log.info("Tražim predavanje koje sadrži id: " + id);
		Predavanje predavanje = predavanjeRepository.findOne(id);
		
		model.addAttribute("vrste", Predavanje.Vrsta.values());
		model.addAttribute("pozicije", Predavac.Pozicija.values());
		model.addAttribute("predavanje", predavanje);
		
		log.info("Prikazujem stranicu za ažuriranje predavanja" + predavanje);
		
		return "azurirajPredavanje";
	}
	
	@PostMapping("/update/{id}")
	public String updatePredavanje(@Valid Predavanje predavanje, Errors errors, Model model) {
		
		if(errors.hasErrors()) {
			log.info("Predavanje ima grešaka. Prekidam slanje.");
			return "azurirajPredavanje";
		}
		
		predavanjeRepository.update(predavanje);
		
		log.info("Predavanje je ažurirano");
		
		return "redirect:/predavanja/prikaziPredavanja";
	}
}
