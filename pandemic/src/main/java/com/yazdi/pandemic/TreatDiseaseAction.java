package com.yazdi.pandemic;

import com.yazdi.pandemic.utils.CustomArrayList;

public class TreatDiseaseAction extends Action {
	
	private Disease disease;
	private City city;
	private CustomArrayList<Disease> diseases;
	
	public TreatDiseaseAction(Disease disease, City city, CustomArrayList<Disease> diseases) {
		this.disease = disease;
		this.city = city;
		this.diseases = diseases;
		this.setType(ActionType.Treat);
	}

	@Override
	public void act() {
		
		Disease disease = this.diseases.findIfCustom(d->d.getName() == this.disease.getName());
		if(disease != null) {
			if(disease.getHasCure()) {
				city.removeAllInfectionCube(disease.getName());
			}
			else city.removeInfectionCube(disease.getName());
		}
	}

}
