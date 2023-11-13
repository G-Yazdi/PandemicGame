package com.yazdi.pandemic.mapcontext.model.entities;

import com.yazdi.pandemic.sharedkernel.contracts.ICity;
import com.yazdi.pandemic.sharedkernel.utils.CustomArrayList;

public class WorldMap {
	
	CustomArrayList<ICity> cities;
	
	public WorldMap() {
		cities = new CustomArrayList<ICity>();
	}
	public ICity getCity(int cityId) {
		return cities.findIfCustom(c -> ((City)c).getId() == cityId);
		
	}
	public CustomArrayList<ICity> getCities() {
		return this.cities;
	}
	public void addCity(ICity newCity) {
		this.cities.add(newCity);
	}

}
