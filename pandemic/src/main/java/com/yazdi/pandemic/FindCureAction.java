package com.yazdi.pandemic;


import java.util.ArrayList;
import java.util.Iterator;

public class FindCureAction implements Action {
	
	private Disease disease;
	private ArrayList<City> cities;
	
	public FindCureAction(Disease disease, ArrayList<City> cities) {
		this.disease = disease;
		this.cities = cities;
	}

	@Override
	public void act() {
		Iterator<City> cityIterator = cities.iterator();
		
		while(cityIterator.hasNext()) {
			((City) cityIterator.next()).getInfectionCubes()
			.forEach((InfectionCube cube)->{
				if(cube.getDisease().getName() == this.disease.getName())
					cube.getDisease().setHasCure(true);
					});
		}
	}

}
