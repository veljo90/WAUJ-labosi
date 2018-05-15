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

import hr.tvz.lisec.data.DvoraneRepository;
import hr.tvz.lisec.entities.Dvorana;

@RestController
@RequestMapping(path="/api/dvorana", produces="application/json")
@CrossOrigin(origins="*")
public class DvoranaRestController {
	
	@Autowired
	DvoraneRepository dvoraneRepository;
	
	@GetMapping
	public Iterable<Dvorana> findAll() {
		return dvoraneRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Dvorana> findOne(@PathVariable Long id) {
		
		Dvorana dvorana = dvoraneRepository.findOne(id);
		if(dvorana != null) {
			return new ResponseEntity<Dvorana>(dvorana, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Dvorana>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes="application/json")
	public Dvorana save(@RequestBody Dvorana dvorana) {
		return dvoraneRepository.save(dvorana);
	}
	
	@PutMapping("/{id}")
	public Dvorana update(@RequestBody Dvorana dvorana) {
		return dvoraneRepository.update(dvorana);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		dvoraneRepository.delete(id);
	}
}
