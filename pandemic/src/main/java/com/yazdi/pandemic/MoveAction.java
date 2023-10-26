package com.yazdi.pandemic;

public class MoveAction implements Action {
	
	private City destination;
	private Player player;
	
	public MoveAction(Player player, City destination) {
		
		if (player.getCurrentLocation().getName() != destination.getName()) {
			 throw new RuntimeException("Illegal move request!");
		}
		this.player = player;
		this.destination = destination;
	}

	@Override
	public void act() {
		this.player.setCurrentLocation(destination);

	}

}
