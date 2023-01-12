package com.study.spring.cloud.weather.job;

import com.example.spring.cloud.weather.service.CityDataService;
import com.example.spring.cloud.weather.service.WeatherDataService;
import com.example.spring.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class WeatherDataSyncJob extends QuartzJobBean {

	//Add logs to an application
	private final static Logger logger=LoggerFactory.getLogger(WeatherDataService.class);

	@Autowired
	private CityDataService cityDataService;

	@Autowired
	private WeatherDataService weatherDataService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		logger.info("Weather Data Sync Job. Start!");

		//Get a list of cities
		List<City> cityList=null;

		try {
			cityList = cityDataService.listCity();
		} catch (Exception e) {
			logger.error("Exception!",e);
		}

		//Traverse the city id to get the weather
		for(City city:cityList){
			String cityId=city.getCityId();
			logger.info("Weather Data Sync Job, cityId:"+cityId);

			weatherDataService.syncDataByCityId(cityId);
		}

		logger.info("Weather Data Sync Job. End!");
	}
}
