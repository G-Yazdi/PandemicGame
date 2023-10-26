package com.yazdi.pandemic;

import java.util.ArrayList;
import java.util.Iterator;

public class TreatDiseaseAction implements Action {
	
	private Disease disease;
	private City city;
	private ArrayList<Disease> diseases;
	
	public TreatDiseaseAction(Disease disease, City city, ArrayList<Disease> diseases) {
		this.disease = disease;
		this.city = city;
		this.diseases = diseases;
	}

	@Override
	public void act() {
		
		Iterator<Disease> diseaseIterator = diseases.iterator();
		while(diseaseIterator.hasNext()) {
			Disease d = diseaseIterator.next();
			if(d.getName() == this.disease.getName() 
					&& d.getHasCure()) {
				
				city.getInfectionCubes().stream().filter(
						cube->cube.getDisease().getName() == this.disease.getName())
		        		.findFirst()
		        		.ifPresent(city.getInfectionCubes()::remove);
				break;
			}	
		}
		
		

	}

}
