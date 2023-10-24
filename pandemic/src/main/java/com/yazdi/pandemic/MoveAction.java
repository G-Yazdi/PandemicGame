package com.yazdi.pandemic;

public class MoveAction implements Action {
	
	private City destination;
	private Player player;
	
	public MoveAction(Player player, City destination) {
		this.player = player;
		this.destination = destination;
	}

	@Override
	public void act() {
		this.player.setCurrentLocation(destination);

	}

}
