package hr.tvz.lisec.data;

import hr.tvz.lisec.entities.Predavanje;

public interface PredavanjeRepository {
	Iterable<Predavanje> findAll();
	Predavanje findOne(String id);
	Predavanje save(Predavanje predavanje);
}
