package hr.tvz.lisec.data;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import hr.tvz.lisec.entities.Dvorana;

@Primary
@Repository
@Transactional
public class HibernateDvoraneRepository implements DvoraneRepository{

	private SessionFactory sessionFactory;
	 
	@Autowired
	public HibernateDvoraneRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Iterable<Dvorana> findAll() {
		return sessionFactory.getCurrentSession().createQuery("SELECT p FROM Dvorana p", Dvorana.class).getResultList();
	}

	@Override
	public Iterable<Dvorana> findAvailable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dvorana findOne(Long id) {
		return sessionFactory.getCurrentSession().find(Dvorana.class, id);
	}

	@Override
	public Dvorana save(Dvorana dvorana) {
		Serializable id = sessionFactory.getCurrentSession().save(dvorana);
		dvorana.setId((long) id);
		return dvorana;
	}

	@Override
	public Dvorana update(Dvorana dvorana) {
		sessionFactory.getCurrentSession().update(dvorana);
		return dvorana;
	}

	@Override
	public void delete(Long id) {
		Dvorana dvorana = findOne(id);
		sessionFactory.getCurrentSession().delete(dvorana);
	}

}
