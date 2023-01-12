package com.example.spring.cloud.weather.service;

import com.example.spring.cloud.weather.vo.Weather;

public interface WeatherReportService {

	//Query weather information based on the city id
	Weather getDataByCityId(String cityId);
}
