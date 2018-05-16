package hr.tvz.lisec.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Predavac {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
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
