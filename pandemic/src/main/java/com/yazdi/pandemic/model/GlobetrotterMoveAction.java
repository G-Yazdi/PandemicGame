package com.yazdi.pandemic.model;

public class GlobetrotterMoveAction extends Action{
	
	private Player player;
	private City destination;

	public GlobetrotterMoveAction(Player player, City destination) {
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
