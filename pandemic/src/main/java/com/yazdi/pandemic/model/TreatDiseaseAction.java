package com.yazdi.pandemic.model;

import com.yazdi.pandemic.utils.CustomArrayList;

public class TreatDiseaseAction extends Action {
	
	private Disease disease;
	private City city;
	private CustomArrayList<Disease> diseases;
	
	public TreatDiseaseAction(Disease disease, City city, CustomArrayList<Disease> diseases) {
		super(ActionType.Treat);
		this.disease = disease;
		this.city = city;
		this.diseases = diseases;
	}

	@Override
	public void perform() {
		
		Disease disease = this.diseases.findIfCustom(d->d.getName() == this.disease.getName());
		if(disease != null) {
			if(disease.getHasCure()) {
				city.removeAllInfectionCube(disease.getName());
			}
			else city.removeInfectionCube(disease.getName());
		}
	}

	@Override
	protected void validate() {
		// TODO Auto-generated method stub
		
	}

}
