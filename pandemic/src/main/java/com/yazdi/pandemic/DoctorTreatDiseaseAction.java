package com.yazdi.pandemic;


public class DoctorTreatDiseaseAction extends Action {
	
	private Disease disease;
	private City city;
	
	public DoctorTreatDiseaseAction(Disease disease, City city) {
		super(ActionType.Treat);
		this.disease = disease;
		this.city = city;
	}

	@Override
	protected void validate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void perform() {
		city.removeAllInfectionCube(disease.getName());
	}
}
