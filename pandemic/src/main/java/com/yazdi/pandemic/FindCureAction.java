package com.yazdi.pandemic;

import com.yazdi.pandemic.utils.CustomArrayList;

public class FindCureAction implements Action {
	
	private Disease disease;
	private CustomArrayList<Disease> diseases;
	
	public FindCureAction(Disease disease, CustomArrayList<Disease> diseases) {
		this.disease = disease;
		this.diseases = diseases;
	}

	@Override
	public void act() {
		Disease disease = this.diseases.findIfCustom(d->d.getName() == this.disease.getName());
		disease.setHasCure(true);
	}

}
