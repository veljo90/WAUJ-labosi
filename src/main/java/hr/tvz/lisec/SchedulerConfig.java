package hr.tvz.lisec;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Scheduler.ObjavaJob;

@Configuration
public class SchedulerConfig {
	
	@Bean
	public JobDetail objavaJobDetail() {
		return JobBuilder.newJob(ObjavaJob.class).withIdentity("objavaJob").storeDurably().build();
	}
	
	@Bean
	public Trigger obajvaJobTrigger() {
		
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();
		
		return TriggerBuilder.newTrigger().forJob(objavaJobDetail()).withIdentity("objavaTrigger").withSchedule(scheduleBuilder).build();
	}
}
