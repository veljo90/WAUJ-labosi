package hr.tvz.lisec.data;

import hr.tvz.lisec.entities.Predavac;

public interface PredavacRepository {
	Iterable<Predavac> findAll();
	Predavac findOne(String id);
	Predavac save(Predavac predavac);
	void update(Predavac predavac);
	void delete(long id);
}
