package com.yazdi.pandemic;

import java.util.ArrayList;

public class Game {
	
	private ArrayList<City> cities;
	private ArrayList<Disease> diseases;
	
	public Game() {
		this.cities = new ArrayList<City>();
		this.diseases = new ArrayList<Disease>();
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

	public ArrayList<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(ArrayList<Disease> diseases) {
		this.diseases = diseases;
	}
	public void addDisease(Disease disease) {
		this.diseases.add(disease);
	}

}
