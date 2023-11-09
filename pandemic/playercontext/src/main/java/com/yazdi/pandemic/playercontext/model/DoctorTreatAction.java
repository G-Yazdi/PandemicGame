package com.yazdi.pandemic.playercontext.model;

public class DoctorTreatAction extends Action {
	
	private Disease disease;
	private Player player;
	
	public DoctorTreatAction(Player player, Disease disease) {
		super(ActionType.Treat);
		this.player = player;
		this.disease = disease;
	}

	@Override
	protected void validate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void perform() {
		this.player.getCurrentLocation().removeAllInfectionCube(disease.getName());
	}
}
