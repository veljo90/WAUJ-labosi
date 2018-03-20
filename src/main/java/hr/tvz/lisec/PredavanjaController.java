package hr.tvz.lisec;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/predavanja")
public class PredavanjaController {

	@GetMapping("/novo")
	public String showForm(Model model) {
		
		model.addAttribute("predavanje", new Predavanje());
		model.addAttribute("vrste", Predavanje.Vrsta.values());
		model.addAttribute("pozicije", Predavac.Pozicija.values());
		
		return "novoPredavanje";
	}
	
	@PostMapping("/novo")
	public String processForm(Predavanje predavanje, Model model) {
		
		model.addAttribute("predavanje", predavanje);
		
		return "predavanjePrihvaceno";
	}
}
