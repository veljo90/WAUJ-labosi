package hr.tvz.lisec.data;

import hr.tvz.lisec.entities.Dvorana;

public interface DvoraneRepository {
	Iterable<Dvorana> findAll();
	Iterable<Dvorana> findAvailable();
}
