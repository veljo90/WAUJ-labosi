package hr.tvz.lisec.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.lisec.data.PredavacRepository;
import hr.tvz.lisec.entities.Predavac;

@RestController
@RequestMapping(path="/api/predavac", produces="application/json")
@CrossOrigin(origins="*")
public class PredavacRestController {
	@Autowired
	PredavacRepository predavacRepository;
	
	@GetMapping
	public Iterable<Predavac> findAll() {
		return predavacRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Predavac> findOne(@PathVariable Long id) {
		
		Optional<Predavac> predavac = predavacRepository.findById(id);
		if(predavac.isPresent()) {
			return new ResponseEntity<Predavac>(predavac.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Predavac>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes="application/json")
	public Predavac save(@RequestBody Predavac predavac) {
		return predavacRepository.save(predavac);
	}
	
	@PutMapping("/{id}")
	public Predavac update(@RequestBody Predavac predavac) {
		return predavacRepository.save(predavac);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		predavacRepository.deleteById(id);
	}
}
