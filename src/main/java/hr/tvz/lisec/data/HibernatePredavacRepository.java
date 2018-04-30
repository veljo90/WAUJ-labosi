package hr.tvz.lisec.data;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import hr.tvz.lisec.entities.Predavac;

@Primary
@Repository
@Transactional
public class HibernatePredavacRepository implements PredavacRepository {

	private SessionFactory sessionFactory;
	 
	@Autowired
	public HibernatePredavacRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Iterable<Predavac> findAll() {
		return sessionFactory.getCurrentSession().createQuery("SELECT p FROM Predavac p", Predavac.class).getResultList();
	}

	@Override
	public Predavac findOne(Long id) {
		return sessionFactory.getCurrentSession().find(Predavac.class, id);
	}

	@Override
	public Predavac save(Predavac predavac) {
		Serializable id = sessionFactory.getCurrentSession().save(predavac);
		predavac.setId((long) id);
		return predavac;
	}

	@Override
	public Predavac update(Predavac predavac) {
		sessionFactory.getCurrentSession().update(predavac);
		return predavac;
	}

	@Override
	public void delete(Long id) {
		Predavac predavac = findOne(id);
		sessionFactory.getCurrentSession().delete(predavac);
	}
	
	

}
