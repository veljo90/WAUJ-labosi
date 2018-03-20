package hr.tvz.lisec;

import lombok.Data;

@Data
public class Predavanje {
	private Predavac predavac;
	private String tema;
	private String sadrzaj;
	private Vrsta vrsta;
	
	public static enum Vrsta {
		PREZENTACIJA,
		RADIONICA
	}
}
