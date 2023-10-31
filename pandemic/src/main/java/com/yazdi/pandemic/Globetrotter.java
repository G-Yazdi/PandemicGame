package com.yazdi.pandemic;

public class Globetrotter extends Action implements Role {
	
	private Player player;
	private City destination;

	public Globetrotter() {
		super(ActionType.Move);
	}

	@Override
	public void perform() {
		this.player.setCurrentLocation(destination);	
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public City getDestination() {
		return destination;
	}

	public void setDestination(City destination) {
		this.destination = destination;
	}
	
	

}
