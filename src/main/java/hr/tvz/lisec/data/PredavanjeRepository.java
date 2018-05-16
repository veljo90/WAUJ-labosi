package hr.tvz.lisec.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import hr.tvz.lisec.entities.Predavac;
import hr.tvz.lisec.entities.Predavanje;

public interface PredavanjeRepository extends CrudRepository<Predavanje, Long> {
	Iterable<Predavanje> findAll();
	Optional<Predavanje> findById(Long id);
	@SuppressWarnings("unchecked")
	Predavanje save(Predavanje predavanje);
	Iterable<Predavanje> findByPredavac_ImeContainingAndPredavac_PozicijaAndTemaContainingIgnoreCaseAndSadrzajContainingAndVrstaAndObjavljeno(String ime, Predavac.Pozicija pozicija, String tema, String sadrzaj, Predavanje.Vrsta vrsta, Boolean objavljeno);
	Iterable<Predavanje> findByPredavac_ImeContainingAndPredavac_PozicijaAndTemaContainingIgnoreCaseAndSadrzajContainingAndObjavljeno(String ime, Predavac.Pozicija pozicija, String tema, String sadrzaj, Boolean objavljeno);
	Iterable<Predavanje> findByPredavac_ImeContainingAndTemaContainingIgnoreCaseAndSadrzajContainingAndVrstaAndObjavljeno(String ime, String tema, String sadrzaj, Predavanje.Vrsta vrsta, Boolean objavljeno);
	Iterable<Predavanje> findByPredavac_ImeContainingAndTemaContainingIgnoreCaseAndSadrzajContainingAndObjavljeno(String ime, String tema, String sadrzaj, Boolean objavljeno);
	void deleteById(Long id);
}
