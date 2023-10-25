package com.yazdi.pandemic;

import java.util.ArrayList;

public class Game {
	
	private ArrayList<City> cities;
	
	public Game() {
		this.cities = new ArrayList<City>();
	}

	public ArrayList<City> getCities() {
		return cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}
	
	public void addCity(City city) {
		this.cities.add(city);
	}

}
