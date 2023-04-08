package com.example.spring.cloud.weather.job;

import com.example.spring.cloud.weather.service.WeatherDataCollectionService;
import com.example.spring.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataSyncJob extends QuartzJobBean {

	//Add logs to an application
		private final static Logger logger=LoggerFactory.getLogger(WeatherDataCollectionService.class);

		@Autowired
		private WeatherDataCollectionService weatherDataCollectionService;

		@Override
		protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

			logger.info("Weather Data Sync Job. Start!");

			//Get a list of cities
			//TODO instead provides data from the city Data API microservice
			List<City> cityList=null;

			try {
				//TODO instead provides data from the city Data API microservice
				cityList = new ArrayList<>();
				City city=new City();
				city.setCityId("101020100");
				cityList.add(city);

			} catch (Exception e) {
				logger.error("Exception!",e);
			}

			//Traverse the city id to get the weather
			for(City city:cityList){
				String cityId=city.getCityId();
				logger.info("Weather Data Sync Job, cityId:"+cityId);

				weatherDataCollectionService.syncDataByCityId(cityId);
			}

			logger.info("Weather Data Sync Job. End!");
		}
	}
