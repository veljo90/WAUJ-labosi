package hr.tvz.lisec.rest;

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

import hr.tvz.lisec.data.PredavanjeRepository;
import hr.tvz.lisec.entities.Predavanje;

@RestController
@RequestMapping(path="/api/predavanje", produces="application/json")
@CrossOrigin(origins="*")
public class PredavanjeRestController {

	@Autowired
	PredavanjeRepository predavanjeRepository;
	
	@GetMapping
	public Iterable<Predavanje> findAll() {
		return predavanjeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Predavanje> findOne(@PathVariable Long id) {
		
		Predavanje predavanje = predavanjeRepository.findOne(id);
		if(predavanje != null) {
			return new ResponseEntity<Predavanje>(predavanje, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Predavanje>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes="application/json")
	public Predavanje save(@RequestBody Predavanje predavanje) {
		return predavanjeRepository.save(predavanje);
	}
	
	@PutMapping("/{id}")
	public Predavanje update(@RequestBody Predavanje predavanje) {
		return predavanjeRepository.update(predavanje);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		predavanjeRepository.delete(id);
	}
}
