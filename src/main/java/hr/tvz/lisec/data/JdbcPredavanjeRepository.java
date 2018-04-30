package hr.tvz.lisec.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import hr.tvz.lisec.entities.Predavac;
import hr.tvz.lisec.entities.Predavanje;

@Repository
public class JdbcPredavanjeRepository implements PredavanjeRepository {
	
	private final String FIND_ALL_QUERRY = "Select Predavanje.id, tema, sadrzaj, vrsta, objavljeno, Predavac.id, ime, pozicija from Predavanje_Predavac as PP join Predavanje on PP.predavanje = predavanje.id join Predavac on PP.predavac = Predavac.id";

	private JdbcTemplate jdbc;
	private SimpleJdbcInsert predavanjeInserter;
	private SimpleJdbcInsert predavanjePredavacInserter;
	
	private PredavacRepository predavacRepository;
	
	public JdbcPredavanjeRepository(JdbcTemplate jdbc, PredavacRepository predavacRepository) {
		this.jdbc = jdbc;
		this.predavanjeInserter = new SimpleJdbcInsert(jdbc)
				.withTableName("Predavanje")
				.usingGeneratedKeyColumns("id");
		
		this.predavanjePredavacInserter = new SimpleJdbcInsert(jdbc)
				.withTableName("Predavanje_Predavac");
		
		this.predavacRepository = predavacRepository;
	}

	
	@Override
	public Iterable<Predavanje> findAll() {
		return jdbc.query(FIND_ALL_QUERRY, this::mapRowToPredavanje);
	}

	@Override
	public Predavanje findOne(Long id) {
		return jdbc.queryForObject(FIND_ALL_QUERRY + "where id = ?", this::mapRowToPredavanje);
	}

	@Override
	public Predavanje save(Predavanje predavanje) {
		predavanje.setId(savePredavanjeDetails(predavanje));
		Predavac predavac = predavacRepository.save(predavanje.getPredavac());
		savePredavanjePredavac(predavac.getId(), predavanje.getId());
		
		return predavanje;
	}
	
	private long savePredavanjeDetails(Predavanje predavanje) {
		Map<String, Object> values = new HashMap<>();
		
		values.put("tema", predavanje.getTema());
		values.put("sadrzaj", predavanje.getSadrzaj());
		values.put("vrsta", predavanje.getVrsta());
		values.put("objavljeno", predavanje.getObjavljeno());
		
		return predavanjeInserter.executeAndReturnKey(values).longValue();
	}
	
	private void savePredavanjePredavac(long predavacId, long predavanjeId) {
		Map<String, Object> values = new HashMap<>();
		
		values.put("predavanje", predavanjeId);
		values.put("predavac", predavacId);
		
		predavanjePredavacInserter.execute(values);
	}
	
	private Predavanje mapRowToPredavanje(ResultSet rs, int rowNum) throws SQLException{
		Predavanje predavanje = new Predavanje();
		
		predavanje.setId(rs.getLong("predavanje.id"));
		predavanje.setTema(rs.getString("tema"));
		predavanje.setSadrzaj(rs.getString("sadrzaj"));
		predavanje.setVrsta(Predavanje.Vrsta.valueOf(rs.getString("vrsta")));
		predavanje.setObjavljeno(rs.getBoolean("objavljeno"));
		
		Predavac predavac = new Predavac();
		predavac.setId(rs.getLong("predavac.id"));
		predavac.setIme(rs.getString("ime"));
		predavac.setPozicija(Predavac.Pozicija.valueOf(rs.getString("pozicija")));
		
		predavanje.setPredavac(predavac);
		
		return predavanje;
	}


	@Override
	public Predavanje update(Predavanje predavanje) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
}
