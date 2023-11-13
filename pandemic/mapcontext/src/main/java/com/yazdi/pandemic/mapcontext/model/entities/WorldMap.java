package com.yazdi.pandemic.mapcontext.model.entities;


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
