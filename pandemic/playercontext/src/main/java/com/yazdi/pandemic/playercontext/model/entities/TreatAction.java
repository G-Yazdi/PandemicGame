package com.yazdi.pandemic.playercontext.model.entities;

import com.yazdi.pandemic.playercontext.model.contracts.Action;
import com.yazdi.pandemic.playercontext.model.enums.ActionType;

public class TreatAction extends Action {
	
	private Disease disease;
	private Player player;
	
	public TreatAction(Player player, Disease disease) {
		super(ActionType.Treat);
		this.player = player;
		this.disease = disease;
	}

	@Override
	public void perform() {
			if(disease.getHasCure()) {
				player.getCurrentLocation().removeAllInfectionCube(disease.getName());
			}
			else player.getCurrentLocation().removeCube(disease.getName());
	}

	@Override
	protected void validate() {
		// TODO Auto-generated method stub
		
	}

}
