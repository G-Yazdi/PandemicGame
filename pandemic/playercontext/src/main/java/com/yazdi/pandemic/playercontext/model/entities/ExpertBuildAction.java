package com.yazdi.pandemic.playercontext.model.entities;

import com.yazdi.pandemic.playercontext.model.contracts.Action;
import com.yazdi.pandemic.playercontext.model.enums.ActionType;

public class ExpertBuildAction extends Action {
	
	private Player player;

	public ExpertBuildAction(Player player) {
		super(ActionType.Build);
		this.player = player;
	}

	@Override
	protected void validate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void perform() {
		player.getCurrentLocation().setHasResearchStation(true);	
	}
}
