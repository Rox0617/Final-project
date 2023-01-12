package com.study.spring.cloud.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.spring.cloud.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

	//Add logs to an application
	private final static Logger logger=LoggerFactory.getLogger(WeatherDataService.class);

	private static final String WEATHER_URI="http://wthrcdn.etouch.cn/weather_mini?";

	private static final long TIME_OUT=1800L;

	@Autowired
	//Encapsulate the rest client
	private RestTemplate restTemplate;

	@Autowired
	//Encapsulation of the redis api
	private StringRedisTemplate stringRedisTemplate;

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

		String key=uri;
		String strBody=null;
		ObjectMapper mapper=new ObjectMapper();
		WeatherResponse resp=null;
		//The ValueOperations class gets the data in the cache via get()
		ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();

		//Check the cache first. Some caches fetch the data in the cache
		if(stringRedisTemplate.hasKey(key)){
			logger.info("Redis has data");
			strBody = ops.get(key);
		}else{
			logger.info("Redis doesn't have data");
			// If the cache is not available, call the service interface to get it
			// Get a json string
			ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

			//Check whether the status code of ResponseEntity is 200. If the status code is 200, take out the strBody
			if(respString.getStatusCodeValue()==200) {
				strBody=respString.getBody();
			}

			//Data write cache
			ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
		}

		//Use json to deserialize the data we want
		try {
			/*
			 * strBody：The parameters to be parsed are obtained from respString
			 * WeatherResponse.class：The type of object to convert to
			 */
			resp=mapper.readValue(strBody,WeatherResponse.class);
		}catch(IOException e) {
			logger.error("Error!",e);
		}
		
		return resp;
	}

	@Override
	public void syncDataByCityId(String cityId) {
		String uri=WEATHER_URI + "citykey=" + cityId;
		this.saveWeatherData(uri);
	}

	//把天气数据放在缓存中
	private void saveWeatherData(String uri){
		String key=uri;
		String strBody=null;
		//ValueOperations类可通过get()获取缓存中的数据
		ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();

		//调用服务接口来获取
		ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

		//判断ResponseEntity的状态码是否为200，为200时取出strBody
		if(respString.getStatusCodeValue()==200) {
			strBody=respString.getBody();
		}

		//数据写入缓存
		ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);

	}

}
