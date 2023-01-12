package com.example.spring.cloud.weather.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.spring.cloud.weather.vo.WeatherResponse;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	
	private static final String WEATHER_URI="http://wthrcdn.etouch.cn/weather_mini?";

	@Autowired
	// Encapsulate the rest client
	private RestTemplate restTemplate;
	
	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		
		String uri=WEATHER_URI + "citykey=" + cityId;
		return this.doGetWeather(uri);
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		
		String uri=WEATHER_URI + "city=" + cityName;
		return this.doGetWeather(uri);
	}
	
	private WeatherResponse doGetWeather(String uri) {
		
		//Get a json string
		ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
		
		//Converts the json string to the desired WeatherResponse type
		ObjectMapper mapper=new ObjectMapper();
		WeatherResponse resp=null;
		String strBody=null;
		
		//Check whether the status code of ResponseEntity is 200. If the status code is 200, take out the strBody
		if(respString.getStatusCodeValue()==200) {
			strBody=respString.getBody();
		}
		
		try {
			/*
			 * strBody：The parameters to be parsed are obtained from respString
			 * WeatherResponse.class：The type of object to convert to
			 */
			resp=mapper.readValue(strBody,WeatherResponse.class);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return resp;
	}

}
