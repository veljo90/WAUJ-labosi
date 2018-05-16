package hr.tvz.lisec.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Predavanje {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@OneToOne(targetEntity=Predavac.class, cascade=CascadeType.ALL)
	@JoinTable(name="Predavanje_Predavac",
			   joinColumns=@JoinColumn(name="Predavanje"),
			   inverseJoinColumns=@JoinColumn(name="Predavac"))
	private Predavac predavac;
	
	//@NotEmpty(message = "Niste unijeli temu predavanja")
	@NotNull
	@Size(min = 5, max = 20, message = "Tema mora sadržavati između 5 i 20 znakova")
	private String tema;
	
	//@NotEmpty(message = "Niste unijeli sadržaj predavanja")
	@NotNull
	@Size(min = 30, max = 300, message = "Sadržaj mora sadržavati između 30 i 300 znakova")
	private String sadrzaj;
	
	@NotNull(message = "Niste odabrali vrstu predavanja")
	private Vrsta vrsta;
	
	private Boolean objavljeno = true;
	
	public static enum Vrsta {
		PREZENTACIJA,
		RADIONICA
	}
}
