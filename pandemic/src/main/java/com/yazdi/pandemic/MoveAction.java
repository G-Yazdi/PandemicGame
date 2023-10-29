package com.yazdi.pandemic;

public class MoveAction implements Action {
	
	private City destination;
	private Player player;
	
	public MoveAction(Player player, City destination) {
		
		if (player.getCurrentLocation().getNeighbours().findIfCustom(c->c == destination) == null) {
			 throw new RuntimeException("Illegal move request: The player can not move to a non-neighbor city!");
		}
		this.player = player;
		this.destination = destination;
	}

	@Override
	public void act() {
		this.player.setCurrentLocation(destination);

	}

}
