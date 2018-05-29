package hr.tvz.lisec.entities;

import java.time.LocalDateTime;

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
	
	private LocalDateTime datumUpisa;
	
	@Valid
	@OneToOne(targetEntity=Predavac.class, cascade=CascadeType.ALL)
	@JoinTable(name="Predavanje_Predavac",
			   joinColumns=@JoinColumn(name="Predavanje"),
			   inverseJoinColumns=@JoinColumn(name="Predavac"))
	private Predavac predavac;
	
	//@NotEmpty(message = "Niste unijeli temu predavanja")
	@NotNull
	@Size(min = 5, max = 20, message = "{msg.validation.subject}")
	private String tema;
	
	//@NotEmpty(message = "Niste unijeli sadržaj predavanja")
	@NotNull
	@Size(min = 30, max = 300, message = "{msg.validation.abstract}")
	private String sadrzaj;
	
	@NotNull(message = "{msg.validation.type}")
	private Vrsta vrsta;
	
	private Boolean objavljeno = true;
	
	public static enum Vrsta {
		PREZENTACIJA,
		RADIONICA
	}
}
