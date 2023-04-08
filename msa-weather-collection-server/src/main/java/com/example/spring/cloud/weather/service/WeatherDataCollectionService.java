package com.example.spring.cloud.weather.service;

public interface WeatherDataCollectionService {
	
	//Synchronize weather based on city ID
		void syncDataByCityId(String cityId);

}
