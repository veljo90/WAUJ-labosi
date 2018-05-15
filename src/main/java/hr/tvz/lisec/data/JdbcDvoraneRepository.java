package hr.tvz.lisec.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hr.tvz.lisec.entities.Dvorana;

@Repository
public class JdbcDvoraneRepository implements DvoraneRepository {

	private JdbcTemplate jdbc;
	
	public JdbcDvoraneRepository(JdbcTemplate jdbc) {
		
		this.jdbc = jdbc;
	}
	
	@Override
	public Iterable<Dvorana> findAll() {
		return jdbc.query("select id, naziv, kapacitet, dostupnost from Dvorana", this::mapRowToDvorana);
	}

	@Override
	public Iterable<Dvorana> findAvailable() {
		return jdbc.query("select id, naziv, kapacitet, dostupnost from Dvorana where dostupnost = TRUE ", this::mapRowToDvorana);
	}
	
	private Dvorana mapRowToDvorana(ResultSet rs, int rowNum) throws SQLException{
		Dvorana dvorana = new Dvorana();
		
		dvorana.setId(rs.getLong("id"));
		dvorana.setNaziv(rs.getString("naziv"));
		dvorana.setKapacitet(rs.getInt("kapacitet"));
		dvorana.setDostupnost(rs.getBoolean("dostupnost"));
		
		return dvorana;
	}

	@Override
	public Dvorana findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dvorana save(Dvorana dvorana) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dvorana update(Dvorana dvorana) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
