package hr.tvz.lisec.data;

import hr.tvz.lisec.entities.Predavac;

public interface PredavacRepository {
	Iterable<Predavac> findAll();
	Predavac findOne(Long id);
	Predavac save(Predavac predavac);
	Predavac update(Predavac predavac);
	void delete(Long id);
}
