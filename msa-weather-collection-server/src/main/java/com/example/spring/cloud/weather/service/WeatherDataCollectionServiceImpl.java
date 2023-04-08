package com.example.spring.cloud.weather.service;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.data.redis.core.StringRedisTemplate;
	import org.springframework.data.redis.core.ValueOperations;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.client.RestTemplate;

	import java.util.concurrent.TimeUnit;

	@Service
	public class WeatherDataCollectionServiceImpl implements WeatherDataCollectionService {

		private static final String WEATHER_URI="http://wthrcdn.etouch.cn/weather_mini?";

		private static final long TIME_OUT=1800L;

		@Autowired
		//Encapsulate the rest client
		private RestTemplate restTemplate;

		@Autowired
		//Encapsulation of the redis api
		private StringRedisTemplate stringRedisTemplate;


	@Override
	public void syncDataByCityId(String cityId) {
		String uri=WEATHER_URI + "citykey=" + cityId;
		this.saveWeatherData(uri);
	}
		// TODO Auto-generated method stub

	//Put the weather data in the cache
		private void saveWeatherData(String uri){
			String key=uri;
			String strBody=null;
			//The ValueOperations class gets the data in the cache via get()
			ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();

			//Call the service interface to get
			ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

			//Check whether the status code of ResponseEntity is 200. If the status code is 200, take out the strBody
			if(respString.getStatusCodeValue()==200) {
				strBody=respString.getBody();
			}

			//Data write cache
			ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);

		}

	}

	}

}
