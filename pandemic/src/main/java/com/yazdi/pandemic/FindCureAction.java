package com.yazdi.pandemic;


import java.util.ArrayList;
import java.util.Iterator;

public class FindCureAction implements Action {
	
	private Disease disease;
	private ArrayList<Disease> diseases;
	
	public FindCureAction(Disease disease, ArrayList<Disease> diseases) {
		this.disease = disease;
		this.diseases = diseases;
	}

	@Override
	public void act() {
		Iterator<Disease> diseaseIterator = diseases.iterator();
		while(diseaseIterator.hasNext()) {
			Disease d = diseaseIterator.next();
			if(d.getName() == this.disease.getName()) {
				d.setHasCure(true);
				break;
			}	
		}
	}

}
