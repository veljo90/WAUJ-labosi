package hr.tvz.lisec.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import hr.tvz.lisec.entities.Predavac;

public interface PredavacRepository extends CrudRepository<Predavac, Long>{
	Iterable<Predavac> findAll();
	Optional<Predavac> findById(Long id);
	@SuppressWarnings("unchecked")
	Predavac save(Predavac predavac);
	void deleteById(Long id);
}
