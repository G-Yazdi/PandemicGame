package com.yazdi.pandemic.service;

import com.yazdi.pandemic.model.City;
import com.yazdi.pandemic.utils.CustomArrayList;

public interface GameService {
	
	CustomArrayList<City> getCities();

	void setCities(CustomArrayList<City> cities);
	
	void addCity(City city);

}
