package com.yazdi.pandemic;

public class GlobetrotterMoveAction extends Action {

	private City destination;
	private Player player;
	
	public GlobetrotterMoveAction(Player player, City destination) {
		super(ActionType.Move);
		this.player = player;
		this.destination = destination;
	}

	public void perform() {
		this.player.setCurrentLocation(destination);

	}

}
