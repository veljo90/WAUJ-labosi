package hr.tvz.lisec.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Predavac {

	private long id;
	
	//@NotEmpty(message = "Niste unijeli ime predavača")
	@NotNull
	@Size(min = 2, max = 20, message = "Ime treba sadržavati između 2 i 20 znakova")
	private String ime;
	
	@NotNull(message = "Niste odabrali poziciju predavača")
	private Pozicija pozicija;
	
	public static enum Pozicija {
		JUNIOR,
		MID,
		SENIOR
	}
}
