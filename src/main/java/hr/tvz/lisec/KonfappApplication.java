package hr.tvz.lisec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)
public class KonfappApplication {

	public static void main(String[] args) {
		SpringApplication.run(KonfappApplication.class, args);
	}
}
