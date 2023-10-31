package com.yazdi.pandemic;

public class Expert extends Action implements Role {
	
	private Player player;
	
	public Expert() {
		super(ActionType.Build);
	}

	@Override
	public void perform() {
		player.getCurrentLocation().setHasResearchStation(true);
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	protected void validate() {
		// TODO Auto-generated method stub
		
	}
}
