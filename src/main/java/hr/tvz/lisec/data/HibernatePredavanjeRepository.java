package hr.tvz.lisec.data;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import hr.tvz.lisec.entities.Predavanje;

@Primary
@Repository
@Transactional
public class HibernatePredavanjeRepository implements PredavanjeRepository {

	private SessionFactory sessionFactory;
	 
	@Autowired
	public HibernatePredavanjeRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public Iterable<Predavanje> findAll() {
		return sessionFactory.getCurrentSession().createQuery("SELECT p FROM Predavanje p", Predavanje.class).getResultList();
	}

	@Override
	public Predavanje findOne(long id) {
		return sessionFactory.getCurrentSession().find(Predavanje.class, id);
	}

	@Override
	public Predavanje save(Predavanje predavanje) {
		Serializable id = sessionFactory.getCurrentSession().save(predavanje);
		predavanje.setId((long) id);
		return predavanje;
	}


	@Override
	public void update(Predavanje predavanje) {
		sessionFactory.getCurrentSession().update(predavanje);
	}

	@Override
	public void delete(long id) {
		
		Predavanje predavanje = findOne(id);
		
		if (predavanje != null) {
			sessionFactory.getCurrentSession().delete(predavanje);
		}
	}

}
