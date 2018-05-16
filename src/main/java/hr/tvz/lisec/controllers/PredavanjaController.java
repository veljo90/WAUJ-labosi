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
	
	@GetMapping("/pretraziPredavanja")
	public String searchClasses(Model model) {
		log.info("Prikazujem stranicu za pretragu predavanja");

		model.addAttribute("predavanje", new Predavanje());
		model.addAttribute("vrste", Predavanje.Vrsta.values());
		model.addAttribute("pozicije", Predavac.Pozicija.values());
		
		return "pretragaPredavanja";
	}
	
	@PostMapping("/pretraziPredavanja")
	public String processSearchClasses(Predavanje predavanje, Model model) {
		log.info("Pretražujem predavanje:" + predavanje);
		
		String ime = predavanje.getPredavac().getIme();
		if (ime == null)
			ime = "";

		String tema = predavanje.getTema();
		if (tema == null) {
			tema = "";
		}
		String sadrzaj = predavanje.getSadrzaj();
		if (sadrzaj == null)
			sadrzaj = "";
		
		ArrayList<Predavanje> popisPredavanja = null;
		
		if (predavanje.getPredavac().getPozicija() == null && predavanje.getVrsta() == null) {
			popisPredavanja = (ArrayList<Predavanje>) predavanjeRepository.findByPredavac_ImeContainingAndTemaContainingIgnoreCaseAndSadrzajContainingAndObjavljeno(ime, tema, sadrzaj, predavanje.getObjavljeno());
		} else if (predavanje.getPredavac().getPozicija() == null) {
			popisPredavanja = (ArrayList<Predavanje>) predavanjeRepository.findByPredavac_ImeContainingAndTemaContainingIgnoreCaseAndSadrzajContainingAndVrstaAndObjavljeno(ime, tema, sadrzaj, predavanje.getVrsta(), predavanje.getObjavljeno());
		} else if (predavanje.getVrsta() == null) {
			popisPredavanja = (ArrayList<Predavanje>) predavanjeRepository.findByPredavac_ImeContainingAndPredavac_PozicijaAndTemaContainingIgnoreCaseAndSadrzajContainingAndObjavljeno(ime, predavanje.getPredavac().getPozicija(), tema, sadrzaj, predavanje.getObjavljeno());
		} else {
			popisPredavanja = (ArrayList<Predavanje>) predavanjeRepository.findByPredavac_ImeContainingAndPredavac_PozicijaAndTemaContainingIgnoreCaseAndSadrzajContainingAndVrstaAndObjavljeno(ime, predavanje.getPredavac().getPozicija(), tema, sadrzaj, predavanje.getVrsta(), predavanje.getObjavljeno());
		}
		
		model.addAttribute("popisPredavanja", popisPredavanja);
		
		return "pretragaPredavanja";
	}
	
	
}
