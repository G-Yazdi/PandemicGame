package com.yazdi.pandemic.model;

public class InfectionCube {
	
	private Disease disease;
	
	public InfectionCube(Disease disease) {
		this.setDisease(disease);
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

}
