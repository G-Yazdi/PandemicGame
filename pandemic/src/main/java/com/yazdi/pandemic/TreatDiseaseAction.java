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
		
		Disease disease = this.diseases.findIfCustom(d->d.getName() == this.disease.getName());
		if(disease != null) {
			if(disease.getHasCure()) {
				city.removeInfectionCube(null);
			}
			else city.removeInfectionCube(this.disease.getName());
		}
	}

}
