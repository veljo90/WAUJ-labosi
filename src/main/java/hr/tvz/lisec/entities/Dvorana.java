package hr.tvz.lisec.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Dvorana {
	
	private long id;
	
	@NotNull
	@Size(min = 2, max = 20, message = "Naziv treba sadržavati između 2 i 20 znakova")
	private String naziv;
	
	@Size(min = 1, message = "Kapacitet treba biti minimalno 1")
	private int kapacitet;
	
	private Boolean dostupnost;
}
