package hr.tvz.lisec.data;

import hr.tvz.lisec.entities.Predavanje;

public interface PredavanjeRepository {
	Iterable<Predavanje> findAll();
	Predavanje findOne(Long id);
	Predavanje save(Predavanje predavanje);
	Predavanje update(Predavanje predavanje);
	void delete(Long id);
}
