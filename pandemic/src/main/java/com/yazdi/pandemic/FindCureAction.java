package com.yazdi.pandemic;

public class FindCureAction implements Action {
	
	private Disease disease;
	
	public FindCureAction(Disease disease) {
		this.disease = disease;
	}

	@Override
	public void act() {
		disease.setHasCure(true);
		//notifyAllCities()
	}

}
