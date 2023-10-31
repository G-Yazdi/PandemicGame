package com.yazdi.pandemic.model;

import com.yazdi.pandemic.utils.CustomArrayList;

public class TreatDiseaseCommand implements Command {
	
	private Action action;
	
	public TreatDiseaseCommand(Disease disease, Player player, CustomArrayList<Disease> diseases) {
		if(player.getRole()!= null 
				&& player.getRole().getType() == ActionType.Treat) {
			action = new DoctorTreatDiseaseAction(disease, player.getCurrentLocation());
			
		}
		else
			action = new TreatDiseaseAction(disease, player.getCurrentLocation(), diseases);
	}

	@Override
	public void execute() {
		action.perform();

	}

}
