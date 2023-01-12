package com.example.spring.cloud.weather.config;

import com.example.spring.cloud.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {
	
	//Renewal frequency
	private static final int TIME = 1800;

	//JobDetail，Define a specific Job
	@Bean
	public JobDetail weatherDataSyncJobDetail(){
		return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob")
				.storeDurably().build();
	}

	//Trigger，Define when and how a particular Job is triggered
	@Bean
	public Trigger weatherDataSyncTrigger(){

		SimpleScheduleBuilder schedBuilder=SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(2).repeatForever();

		return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
				.withIdentity("weatherDataSyncTrigger").withSchedule(schedBuilder).build();
	}
}
