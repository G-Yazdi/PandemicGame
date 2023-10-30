package com.yazdi.pandemic;

public class GlobetrotterMoveAction extends Action {

	private City destination;
	private Player player;
	
	public GlobetrotterMoveAction(Player player, City destination) {
		
		this.player = player;
		this.destination = destination;
		this.setType(ActionType.Move);
	}

	public void perform() {
		this.player.setCurrentLocation(destination);

	}

}
