package com.yazdi.pandemic;

import java.util.ArrayList;

public class Player {
	
	private City currentLocation;
	private ArrayList<PlayerCard> hand;
	
	public Player() {
		this.hand = new ArrayList<PlayerCard>();
	}

	public City getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(City currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public void act(Action action) {
		action.act();
	}

	public ArrayList<PlayerCard> getHand() {
		return hand;
	}

	public void setHand(ArrayList<PlayerCard> playerCards) {
		this.hand = playerCards;
	}
	
	public void addToHand(PlayerCard playerCard) {
		this.hand.add(playerCard);
	}
	
	
}
