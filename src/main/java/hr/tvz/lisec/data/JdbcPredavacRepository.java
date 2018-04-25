package hr.tvz.lisec.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import hr.tvz.lisec.entities.Predavac;

@Repository
public class JdbcPredavacRepository implements PredavacRepository {

	private JdbcTemplate jdbc;
	private SimpleJdbcInsert predavacInserter;
	
	@Autowired
	public JdbcPredavacRepository(JdbcTemplate jdbc) {
		
		this.jdbc = jdbc;
		this.predavacInserter = new SimpleJdbcInsert(jdbc)
				.withTableName("Predavac")
				.usingGeneratedKeyColumns("id");
	}
	
	@Override
	public Iterable<Predavac> findAll() {
		return jdbc.query("select id, ime, pozicija from Predavac", this::mapRowToPredavac);
	}

	@Override
	public Predavac findOne(String id) {
		return jdbc.queryForObject("select id, ime, pozicija from Predavac where id = ?", this::mapRowToPredavac, id);
	}

	@Override
	public Predavac save(Predavac predavac) {
		predavac.setId(savePredavacDetails(predavac));
		
		return predavac;
	}
	
	private long savePredavacDetails(Predavac predavac) {
		Map<String, Object> values = new HashMap<>();
		values.put("ime", predavac.getIme());
		values.put("pozicija", predavac.getPozicija());
		
		return predavacInserter.executeAndReturnKey(values).longValue();
	}
	
	private Predavac mapRowToPredavac(ResultSet rs, int rowNum) throws SQLException{
		Predavac predavac = new Predavac();
		predavac.setId(rs.getLong("id"));
		predavac.setIme(rs.getString("ime"));
		predavac.setPozicija(Predavac.Pozicija.valueOf(rs.getString("pozicija")));
		
		return predavac;
	}

	@Override
	public void update(Predavac predavac) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
}
