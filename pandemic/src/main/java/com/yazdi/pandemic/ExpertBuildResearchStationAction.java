package com.yazdi.pandemic;

public class ExpertBuildResearchStationAction extends Action {

	private Player player;
	
	public ExpertBuildResearchStationAction(Player player) {
		
		this.player = player;
		this.setType(ActionType.Build);
	}

	public void perform() {
		player.getCurrentLocation().setHasResearchStation(true);

	}


}
