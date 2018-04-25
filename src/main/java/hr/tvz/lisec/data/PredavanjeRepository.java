package hr.tvz.lisec.data;

import hr.tvz.lisec.entities.Predavanje;

public interface PredavanjeRepository {
	Iterable<Predavanje> findAll();
	Predavanje findOne(long id);
	Predavanje save(Predavanje predavanje);
	void update(Predavanje predavanje);
	void delete(long id);
}
