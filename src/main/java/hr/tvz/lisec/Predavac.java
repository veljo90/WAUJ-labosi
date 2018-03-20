package hr.tvz.lisec;

import lombok.Data;

@Data
public class Predavac {
	private String ime;
	private Pozicija pozicija;
	
	public static enum Pozicija {
		JUNIOR,
		MID,
		SENIOR
	}
}
