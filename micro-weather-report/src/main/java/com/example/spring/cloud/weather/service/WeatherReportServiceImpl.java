package com.example.spring.cloud.weather.service;

import com.example.spring.cloud.weather.vo.Weather;
import com.example.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

	@Autowired
	private WeatherDataService weatherDataService;

	@Override
	public Weather getDataByCityId(String cityId) {
		WeatherResponse resp=weatherDataService.getDataByCityId(cityId);
		return resp.getData();
	}
}
