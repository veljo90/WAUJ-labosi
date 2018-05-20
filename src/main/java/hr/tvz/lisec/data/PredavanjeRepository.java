package hr.tvz.lisec.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import hr.tvz.lisec.entities.Predavanje;

@RepositoryRestResource(path="/predavanje")
public interface PredavanjeRepository extends CrudRepository<Predavanje, Long> {
	Iterable<Predavanje> findAll();
	Optional<Predavanje> findById(Long id);
	@SuppressWarnings("unchecked")
	Predavanje save(Predavanje predavanje);
	Iterable<Predavanje> findByTemaContaining(String tema);
	void deleteById(Long id);
}
