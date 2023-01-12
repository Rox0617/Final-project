package com.example.spring.cloud.weather.vo;

import java.io.Serializable;

//Implement the serialization interface
public class WeatherResponse implements Serializable {

	// Generate the serialization ID
	private static final long serialVersionUID = 1L;
	
	private Weather data;
	private Integer status;
	private String desc;

	public Weather getData() {
		return data;
	}

	public void setData(Weather data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

