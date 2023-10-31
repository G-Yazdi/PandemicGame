package com.yazdi.pandemic.model;

public class MoveAction extends Action {
	
	private City destination;
	private Player player;
	
	public MoveAction(Player player, City destination) {
		super(ActionType.Move);
		this.player = player;
		this.destination = destination;
	}

	@Override
	public void perform() {
		validate();
		this.player.setCurrentLocation(destination);

	}

	@Override
	protected void validate() {
		if (player.getCurrentLocation().getNeighbours().findIfCustom(c->c == destination) == null) {
			 throw new RuntimeException("Illegal move request: The player can not move to a non-neighbor city!");
		}
	}

}
