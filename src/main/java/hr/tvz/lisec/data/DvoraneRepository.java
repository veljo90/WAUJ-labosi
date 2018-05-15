package hr.tvz.lisec.data;

import hr.tvz.lisec.entities.Dvorana;

public interface DvoraneRepository {
	Iterable<Dvorana> findAll();
	Dvorana findOne(Long id);
	Iterable<Dvorana> findAvailable();
	Dvorana save(Dvorana dvorana);
	Dvorana update(Dvorana dvorana);
	void delete(Long id);
}
