package hr.tvz.lisec.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import hr.tvz.lisec.entities.Predavac;

@RepositoryRestResource(path="/predavac")
public interface PredavacRepository extends CrudRepository<Predavac, Long>{
	Iterable<Predavac> findAll();
	Optional<Predavac> findById(Long id);
	@SuppressWarnings("unchecked")
	Predavac save(Predavac predavac);
	void deleteById(Long id);
}
