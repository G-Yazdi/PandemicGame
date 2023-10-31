package com.yazdi.pandemic;

public class MoveAction extends Action {
	
	private City destination;
	private Player player;
	
	public MoveAction(Player player, City destination) {
		super(ActionType.Move);
		if (player.getCurrentLocation().getNeighbours().findIfCustom(c->c == destination) == null) {
			 throw new RuntimeException("Illegal move request: The player can not move to a non-neighbor city!");
		}
		this.player = player;
		this.destination = destination;
	}

	@Override
	public void perform() {
		this.player.setCurrentLocation(destination);

	}

}
