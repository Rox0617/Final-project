package com.example.spring.cloud.weather.service;

import com.example.spring.cloud.weather.vo.City;

import java.util.List;


public interface CityDataService {

	List<City> listCity() throws Exception;

}
