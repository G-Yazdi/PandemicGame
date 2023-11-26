package com.yazdi.pandemic.playercontext.model.entities;

import com.yazdi.pandemic.sharedkernel.contracts.ICube;

public class Cube implements ICube {
	private Disease disease;
	
	public Cube(Disease disease) {
		this.setDisease(disease);
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

}
