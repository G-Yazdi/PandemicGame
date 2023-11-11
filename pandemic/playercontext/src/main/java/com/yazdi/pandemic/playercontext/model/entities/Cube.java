package com.yazdi.pandemic.playercontext.model.entities;

public class Cube {
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
