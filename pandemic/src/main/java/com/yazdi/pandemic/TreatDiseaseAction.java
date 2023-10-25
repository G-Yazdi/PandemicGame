package com.yazdi.pandemic;


public class TreatDiseaseAction implements Action {
	
	private Disease disease;
	private City city;
	
	public TreatDiseaseAction(Disease disease, City city) {
		this.disease = disease;
		this.city = city;
	}

	@Override
	public void act() {
		
		city.getInfectionCubes().stream()
	        .filter(cube->cube.getDisease().getName() == this.disease.getName()
	        && cube.getDisease().getHasCure())
	        .findFirst()
	        .ifPresent(city.getInfectionCubes()::remove);

	}

}
