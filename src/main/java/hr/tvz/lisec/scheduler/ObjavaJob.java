package hr.tvz.lisec.scheduler;

import java.time.LocalDateTime;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import hr.tvz.lisec.data.PredavanjeRepository;
import hr.tvz.lisec.entities.Predavanje;

public class ObjavaJob extends QuartzJobBean {
	
	@Autowired
	PredavanjeRepository predavanjeRepository;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Iterable<Predavanje> predavanja = predavanjeRepository.findAll();
		
		for(Predavanje predavanje : predavanja) {
			if (novoObjavljenoPredavanje(predavanje)) {
				objaviPredavanje(predavanje);
			}
		}
	}
	
	private Boolean novoObjavljenoPredavanje(Predavanje predavanje) {
		return predavanje.getObjavljeno() && predavanje.getDatumUpisa().isAfter(LocalDateTime.now().minusSeconds(10));
	}
	
	private void objaviPredavanje(Predavanje predavanje) {
		System.out.println("Objavljeno je novo predavanje: " + predavanje.getTema());
	}
}
