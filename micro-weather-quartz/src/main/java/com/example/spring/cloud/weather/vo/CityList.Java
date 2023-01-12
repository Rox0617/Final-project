package com.example.spring.cloud.weather.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

//Declare as the root element of xml
@XmlRootElement(name = "c")
//Declare the access type of xml to be FIELD
@XmlAccessorType(XmlAccessType.FIELD)
public class CityList {

	//Elements declared as xml
	@XmlElement(name = "d")
	private List<City> cityList;

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
}
