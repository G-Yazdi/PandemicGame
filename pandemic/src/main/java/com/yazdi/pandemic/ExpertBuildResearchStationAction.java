package com.yazdi.pandemic;


public class ExpertBuildResearchStationAction extends Action {
	
	private Player player;

	public ExpertBuildResearchStationAction(Player player) {
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
