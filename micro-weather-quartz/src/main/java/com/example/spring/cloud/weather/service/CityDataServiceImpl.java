package com.example.spring.cloud.weather.service;

import com.example.spring.cloud.weather.vo.WeatherResponse;

public interface WeatherDataService {

	//Query weather data by city ID
	WeatherResponse getDataByCityId(String cityId);
	
	//Query weather data by city name
	WeatherResponse getDataByCityName(String cityName);

	//Synchronize weather data based on city id
	void syncDataByCityId(String cityId);
	
}
