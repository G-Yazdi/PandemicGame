package com.yazdi.pandemic.playercontext.model.entities;

import com.yazdi.pandemic.playercontext.model.contracts.Action;
import com.yazdi.pandemic.playercontext.model.enums.ActionType;

public class GlobetrotterMoveAction extends Action{
	
	private Player player;
	private PlayerLocation destination;

	public GlobetrotterMoveAction(Player player, PlayerLocation destination) {
		super(ActionType.Move);
		this.player = player;
		this.destination = destination;
	}

	@Override
	protected void validate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void perform() {
		this.player.setCurrentLocation(destination);
		
	}

}


