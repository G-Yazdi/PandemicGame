package com.yazdi.pandemic;

import com.yazdi.pandemic.utils.CustomArrayList;

public class TreatDiseaseAction implements Action {
	
	private Disease disease;
	private City city;
	private CustomArrayList<Disease> diseases;
	
	public TreatDiseaseAction(Disease disease, City city, CustomArrayList<Disease> diseases) {
		this.disease = disease;
		this.city = city;
		this.diseases = diseases;
	}

	@Override
	public void act() {
		
		if(this.diseases.findIfCustom(d->d.getName() == this.disease.getName() 
					&& d.getHasCure()) != null) {
			city.removeInfectionCube(this.disease.getName());
		}
	}

}
