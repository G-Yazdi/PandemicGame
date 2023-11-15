package com.yazdi.pandemic.playercontext.model.entities;

import com.yazdi.pandemic.playercontext.model.contracts.Action;
import com.yazdi.pandemic.playercontext.model.enums.ActionType;

public class MoveAction extends Action {
	
	private PlayerLocation destination;
	private Player player;
	
	public MoveAction(Player player, PlayerLocation destination) {
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
		if (player.getCurrentLocation().getNeighbours().findIfCustom(c->c == destination.getId()) == null) {
			 throw new RuntimeException("Illegal move request: The player can not move to a non-neighbor city!");
		}
	}

}
